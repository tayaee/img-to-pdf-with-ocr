package com.demo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutputIntent;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.pdfocr.OcrPdfCreator;
import com.itextpdf.pdfocr.OcrPdfCreatorProperties;
import com.itextpdf.pdfocr.tesseract4.Tesseract4LibOcrEngine;
import com.itextpdf.pdfocr.tesseract4.Tesseract4OcrEngineProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://itextpdf.com/en/blog/technical-notes/how-use-itext-pdfocr-recognize-text-scanned-documents
class App {

    private static final Tesseract4OcrEngineProperties tesseract4OcrEngineProperties = new Tesseract4OcrEngineProperties();
    private static final String DEFAULT_TESS_DATA_FOLDER = "C:\\Program Files\\Tesseract-OCR\\tessdata";
    private static final String DEFAULT_RGB_COLOR_PROFILE_PATH = "sRGB_CS_profile.icm";

    private static PdfOutputIntent getRGBPdfOutputIntent() {
        InputStream is = App.class.getClassLoader().getResourceAsStream(DEFAULT_RGB_COLOR_PROFILE_PATH);
        return new PdfOutputIntent("", "", "", "sRGB IEC61966-2.1", is);

    }

    public void imgToPdf(String imageFileName, String pdfFileName) throws IOException {
        File file = new File(pdfFileName);
        if (file.exists() && file.length() > 0) {
            System.out.println("Found " + pdfFileName + ". " + file.length() + " bytes. No writing.");
            return;
        }
        final Tesseract4LibOcrEngine tesseractReader = new Tesseract4LibOcrEngine(tesseract4OcrEngineProperties);
        tesseract4OcrEngineProperties.setPathToTessData(new File(getOrGuessTessDataDir()));
        OcrPdfCreatorProperties properties = new OcrPdfCreatorProperties();
        properties.setPdfLang("en"); //we need to define a language to make it PDF/A compliant
        OcrPdfCreator ocrPdfCreator = new OcrPdfCreator(tesseractReader, properties);
        PdfWriter writer = new PdfWriter(pdfFileName);
        List<File> files = Collections.singletonList(new File(imageFileName));
        PdfDocument pdfA = ocrPdfCreator.createPdfA(files, writer, getRGBPdfOutputIntent());
        pdfA.close();
        writer.close();
        System.out.println("Wrote to " + pdfFileName);
    }

    private static String getOrGuessTessDataDir() throws IOException {
        String tessDataDir = System.getProperty("tessdata-dir");
        if (tessDataDir != null) {
            File file = new File(tessDataDir);
            if (file.exists()) {
                return tessDataDir;
            }
        }
        List<String> appDataVarList = new ArrayList(Arrays.asList(
                "ProgramFiles",
                "ProgramFiles(x86)",
                "LOCALAPPDATA",
                "APPDATA"
        ));
        for (String appDataVar : appDataVarList) {
            String appDataDir = System.getenv(appDataVar);
            File file = new File(appDataDir, "Tesseract-OCR/tessdata");
            if (file.exists()) {
                return file.getCanonicalPath();
            }
        }
        return "";
    }

    private static String getPdfName(String file) {
        int idx = file.lastIndexOf('.');
        if (idx >= 0) {
            return file.substring(0, idx) + ".pdf";
        } else {
            return file + ".pdf";
        }
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        if (args != null) {
            for (String fileName : args) {
                String pdfName = getPdfName(fileName);
                app.imgToPdf(fileName, pdfName);
            }
        }
    }
}