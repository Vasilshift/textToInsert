//package com.textinsert.textToInsert;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//
//import java.io.*;
//
//public class WorkWithFile {
//
//    public static void recieveFile(String newString) throws IOException {
//
//        System.out.println(newString);
//        File file = new File(newString);
//        PDDocument document = PDDocument.load(file);
//        PDFTextStripper stripper = new GetAndSendCoordinate();
//        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
//        stripper.writeText(document, dummy);
//        document.save(newString);
//        document.close();
//    }
//}
