package com.textinsert.textToInsert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Set;

@SpringBootApplication
public class Application {


	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Application.class, args);

		SeekFilesInFolder workWithFilesInFolder = new SeekFilesInFolder();
		Set<String> strings = workWithFilesInFolder.listFilesUsingDirectoryStream("C:/updatepdf/исходники/");

		InsertCoordinatesToDB.createTable();

		String fullStringToFileInMain;

		for (String s : strings ) {

			fullStringToFileInMain = "C:/updatepdf/исходники/".concat(s);
			//CollectStrings.createStringFilename(fullStringToFileInMain);

			GetAndSendCoordinate getAndSendCoordinate = new GetAndSendCoordinate(fullStringToFileInMain);
			getAndSendCoordinate.runer();
			//WorkWithFile.recieveFile(fullStringToFileInMain);   // method for recieve file with OutpuStream



//			Thread thread = new Thread(new GetAndSendCoordinate(fullStringToFileInMain));
//			thread.start();

//			thread.join(6000);
//
//			Thread thread1 = new Thread(new WorkWithText(fullStringToFileInMain));
//			thread1.start();
		}


	}

}
