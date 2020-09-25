package com.demo;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class AppTest extends TestCase {
    public void testImgToPdf() throws IOException {
        // arrange
        File file = new File("testdata/test.pdf");
        if (file.exists())
            file.delete();
        assertTrue(!file.exists());

        // act
        App app = new App();
        app.imgToPdf("testdata/test.png", "testdata/test.pdf");

        // assert
        assertTrue(file.exists());
    }
}
