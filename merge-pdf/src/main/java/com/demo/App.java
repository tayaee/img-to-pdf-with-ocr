package com.demo;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws COSVisitorException, IOException {
        if (args.length >= 2) {
            PDFMergerUtility merger = new PDFMergerUtility();
            for (int i = 1, len = args.length; i < len; i++) {
                System.out.println("Adding " + args[i]);
                merger.addSource(args[i]);
            }
            System.out.println("Producing " + args[0]);
            merger.setDestinationFileName(args[0]);
            merger.mergeDocuments();
        } else {
            System.out.println("mergepdf output.pdf file1.pdf ... fileN.pdf");
        }

    }
}
