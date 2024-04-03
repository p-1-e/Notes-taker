package com.pie;

import com.pie.data.Note;
import com.pie.interfaces.NotesImp;
import com.pie.service.NotesServiceImp;
import com.pie.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class Main {
    // I used this class for testing the in and out methods
    private static final Logger log = LogManager.getLogger();
    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    public static void main(String[] args) throws IOException {
        NotesServiceImp service = new NotesServiceImp(new NotesImp());
        log.info(service.searchByText("nota").toString());
        log.info(service.findAll().toString());
    }

    private static void createFiveNotes(NotesServiceImp service) {
        for (int i = 0; i < 5; i++){
            service.add(new Note());
        }
    }

}