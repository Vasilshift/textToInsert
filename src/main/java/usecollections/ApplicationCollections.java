package usecollections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Set;
// in this version 1. read coordinates from files, 2. write in DB, 3. read from DB, 4. write in texts in files.
@SpringBootApplication
public class ApplicationCollections {


    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ApplicationCollections.class, args);

        SeekFolder workWithFilesInFolder = new SeekFolder();
        Set<String> stringsOfFiles = workWithFilesInFolder.listFilesUsingDirectoryStream("C:/updatepdf/исходники/");

//		WorkWithDB.createTable();
//
		for (String s : stringsOfFiles ) {

			String fullStringToFileInMain = "C:/updatepdf/исходники/".concat(s);

			//WorkWithFile.recieveFile(fullStringToFileInMain);   // method for recieve file with OutpuStream

//			Thread thread = new Thread(new GetAndSendCoordinate(fullStringToFileInMain));
//			thread.start();
//
//			thread.join(6000);
//
//			Thread thread1 = new Thread(new WorkWithText(fullStringToFileInMain));
//			thread1.start();
		}


    }

}