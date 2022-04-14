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

    //private static Logger logger = LogManager.getLogger(GetCoordinate.class);

    public GetAndSendCoordinate() throws IOException {
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

            WorkWithDB.CreateTableAndInsertValueToDB(xDirAdj, yEndAdj, textToFind);
            //WorkWithDB.readFromDB();
            //InsertText.insertText(xDirAdj, yEndAdj);

        }

    }


}

