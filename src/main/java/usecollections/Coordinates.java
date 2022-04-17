package usecollections;

import com.textinsert.textToInsert.GetAndSendCoordinate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class Coordinates extends PDFTextStripper {
    public static String textToFind = "Уникальный номер записи об аккредитации в реестре аккредитованных лиц ________";
    public static String textToFind1 = "должность руководителя или ";
    public static String textToFind2 = "фамилия, инициалы";
    public static String forText = "RA.RU.312849";
    public static String forText1 = "metrolog";
    public static String forText2 = "FIO";

    public Coordinates() throws IOException {
    }
    private String stringForFile;

    //@Autowired
    public Coordinates(String stringForFile) throws IOException {
        this.stringForFile = stringForFile;
    }

//    File file = new File(stringForFile);
//    PDDocument document = PDDocument.load(file);
//    PDFTextStripper stripper = new Coordinates();
//    Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
//            stripper.writeText(document, dummy);
//            document.save(stringForFile);
//            document.close();

}
