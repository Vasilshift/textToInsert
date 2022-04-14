package com.textinsert.textToInsert;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class GetAndSendCoordinate extends PDFTextStripper implements Runnable {

    public static String textToFind = "Уникальный номер записи об аккредитации в реестре аккредитованных лиц ________";
    public static String textToFind1 = "должность руководителя или ";
    public static String textToFind2 = "фамилия, инициалы";
    public static String forText = "RA.RU.312849";
    public static String forText1 = "Main metrolog";
    public static String forText2 = "Petrashev V.V.";

    public GetAndSendCoordinate() throws IOException {
        WorkWithDB.createTable();
    }

    @Override
    public void run() {
        try {
            File file = new File("C:/updatepdf/исходники/исходник1.pdf");
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new GetAndSendCoordinate();
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            document.save("C:/updatepdf/исходники/исходник1.pdf");
            document.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void writeString(String string, List<TextPosition> textPositions) {

        if (string.equalsIgnoreCase(textToFind)) {
            TextPosition textPosition = textPositions.get(string.length() - 7);
            float xDirAdj = textPosition.getXDirAdj();
            float yEndAdj = textPosition.getEndY();

            System.out.println(xDirAdj);
            System.out.println(yEndAdj);

            WorkWithDB.InsertValueToTable(textToFind, xDirAdj, yEndAdj, forText);
        }

        if (string.equalsIgnoreCase(textToFind1)) {
            TextPosition textPosition = textPositions.get(0);
            float xDirAdj1 = textPosition.getXDirAdj();
            float yEndAdj1 = textPosition.getEndY() + 12;

            System.out.println("xDirAdj1 " + xDirAdj1);
            System.out.println("yEndAdj1 " + yEndAdj1);

            WorkWithDB.InsertValueToTable(textToFind1, xDirAdj1, yEndAdj1, forText1);
        }

        if (string.equalsIgnoreCase(textToFind2)) {
            TextPosition textPosition = textPositions.get(0);
            float xDirAdj2 = textPosition.getXDirAdj();
            float yEndAdj2 = textPosition.getEndY() + 12;

            System.out.println("xDirAdj2 " + xDirAdj2);
            System.out.println("yEndAdj2 " + yEndAdj2);

            WorkWithDB.InsertValueToTable(textToFind2, xDirAdj2, yEndAdj2, forText2);
        }

    }

}

