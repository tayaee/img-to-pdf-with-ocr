# img-to-pdf-with-ocr
Convert images to PDF with OCR enabled..

# Quick instruction
  * Install the latest OCR engine from https://digi.bib.uni-mannheim.de/tesseract/
  * Install Java SDK 1.8+
  * Maven 3.x
  * Build it
    * <code>build.bat</code>
  * Test
    * <code>img-to-pdf-with-ocr.bat testdata\test.png</code>
  * Check the output testdata\test.pdf
  * Copy the following two files to a path that is available in PATH variable
    * img-to-pdf-with-ocr.bat
    * target\img-to-pdf-with-ocr-1.0-SNAPSHOT-jar-with-dependencies.jar
# Enjoy!
  * img-to-pdf-with-ocr *.png
  * img-to-pdf-with-ocr *.jpg

# Thanks
  * This code is based https://itextpdf.com/en/blog/technical-notes/how-use-itext-pdfocr-recognize-text-scanned-documents
