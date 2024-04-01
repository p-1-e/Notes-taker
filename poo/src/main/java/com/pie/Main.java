package com.pie;

import com.pie.data.Note;
import com.pie.repository.NotesInterface;
import com.pie.repository.implementatiion.NotesImp;
import com.pie.service.NotesServiceImp;
import com.pie.utils.NoteUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
    // I used this class for testing the in and out methods
    private static final Logger log = LogManager.getLogger();

//    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    public static void main(String[] args) {
        NotesInterface notesInterface = new NotesImp();

        Note note = NoteUtils.textToNote("poo/src/main/resources/99c97c3e-d539-4c5d-b423-be33b5044cb6.txt");
        note.setTitle("title");

    }

}