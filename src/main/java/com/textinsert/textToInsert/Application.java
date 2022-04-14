package com.textinsert.textToInsert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException, InterruptedException, SQLException {
		SpringApplication.run(Application.class, args);

		Thread thread = new Thread(new GetAndSendCoordinate());
		thread.start();

		thread.join(4000);

		Thread thread1 = new Thread(new InsertText());

		thread1.start();

	}

}
