package com.textinsert.textToInsert;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

//@Component
public class CollectStrings {

    @Bean("ert")
    public static String createStringFilename(String fileName) throws IOException {
        //SeekFilesInFolder workWithFilesInFolder = new SeekFilesInFolder();
        //Set<String> strings = workWithFilesInFolder.listFilesUsingDirectoryStream("C:/updatepdf/исходники/");

        //String s = strings.stream().findFirst().get().toString();

        return fileName;
    }
}
