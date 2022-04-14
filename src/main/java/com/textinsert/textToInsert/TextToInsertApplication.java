package com.textinsert.textToInsert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.*;

@SpringBootApplication
public class TextToInsertApplication {

	public static void main(String[] args) throws IOException, InterruptedException, SQLException {
		SpringApplication.run(TextToInsertApplication.class, args);



		Thread thread = new Thread(new GetCoordinate());
		thread.start();

		thread.join(4000);


		Thread thread1 = new Thread(new InsertText());
		thread1.start();
	}

}
