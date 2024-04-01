package com.pie;

import com.pie.data.Note;
import com.pie.repository.NotesInterface;
import com.pie.repository.implementatiion.NotesImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Scanner;

public class Main {
    // I used this class for testing the in and out methods
    private static final Logger log = LogManager.getLogger();
    private static final Scanner sc = new Scanner(System.in); 
    private static NotesInterface noteImpl = new NotesImp();

//    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    public static void main(String[] args) {
        create5Notes();
        List<Note> notes =noteImpl.findAll(); 
        log.info(notes.toString()); 
        editItem(1);
    }

    public static void create5Notes(){
        for(int i = 0; i < 5; i++){
            noteImpl.add(new Note()); 
        }
    }

    public static void editItem(int index){
        log.info("input the new title of the note: ");
        String title = sc.nextLine(); 

        log.info("input the new text of the note: ");
        String text = sc.nextLine(); 

        Note note =noteImpl.item(index); 
        note.setTitle(title);
        note.setText(text);
    }

}