package com.pie;

import com.pie.data.Note;
import com.pie.interfaces.NotesImp;
import com.pie.service.NotesServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
    // I used this class for testing the in and out methods
    private static final Logger log = LogManager.getLogger();


//    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    public static void main(String[] args) {
        //writeTest();
        //writeTest();
        //log.info(Main::readTest);

        //Read file
        NotesServiceImp notesServiceImp = new NotesServiceImp(new NotesImp());


        List< Note> notes = notesServiceImp.add(new Note());
        log.info(notes.toString());

    }

//    private static void cleanTest() {
//        FileUtils.clean(FILE_ADDRESS);
//    }
//
//    private static String readTest() {
//        return FileUtils.read(FILE_ADDRESS);
//    }
//
//    private static void writeTest() {
//        FileUtils.write(FILE_ADDRESS, " message, ");
//    }
//

}