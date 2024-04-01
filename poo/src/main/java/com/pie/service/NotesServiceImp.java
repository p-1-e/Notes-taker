package com.pie.service;

import com.pie.data.Note;
import com.pie.repository.NotesInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class NotesServiceImp {
    private static final Logger log = LogManager.getLogger();
    private final NotesInterface notesInterface;

    public NotesServiceImp(NotesInterface notesInterface) {
        this.notesInterface = notesInterface;
    }

    public String Read(){
        var note = notesInterface.read();

        if(note.isEmpty()){
            log.info("La nota esta vacia");
        }
        return note;
    }

    public Note write(Note note){
        Optional<Note> optNote = notesInterface.write(note);

        if(optNote.isEmpty()){
            log.info("La nota esta vacia");
        }
        return optNote.get();
    }

    public List<Note> findAll(){
        return  notesInterface.findAll();
    }

    public List<Note> add(Note note){
        return notesInterface.add(note);
    }
}
