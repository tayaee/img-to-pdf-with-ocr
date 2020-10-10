# img-to-pdf-with-ocr
Convert images to PDF with OCR enabled..

# Build
  * Use Java SDK 1.8+ and Maveen 3+ for build.  
  * Build it <code>mvn clean package -DskipTests</code>
    
# Install
  * Install the latest OCR engine from https://digi.bib.uni-mannheim.de/tesseract/. The 'tessdata' directory from this installation is referred by img-to-pdf-with-ocr.
  * Copy the following two files to a path that is available in PATH variable
    * copy img-to-pdf-with-ocr.bat YOUR-BIN\
    * copy target\img-to-pdf-with-ocr-1.0-SNAPSHOT-jar-with-dependencies.jar YOUR-BIN\
    
# Enjoy!
  * Usage: <code>img-to-pdf-with-ocr.bat *.png *.jpg</code>

# Thanks
  * This code is based on https://itextpdf.com/en/blog/technical-notes/how-use-itext-pdfocr-recognize-text-scanned-documents
