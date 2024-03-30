package com.pie.service;

import com.pie.data.Note;
import com.pie.interfaces.NotesInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.SEPARATOR;

public class NotesServiceImp {
    private static final Logger log = LogManager.getLogger();
    private NotesInterface notesInterface;

    public NotesServiceImp(NotesInterface notesInterface) {
        this.notesInterface = notesInterface;
    }

    public String Read(){
        var note = notesInterface.Read();

        if(note.isEmpty()){
            log.info("La nota esta vacia");
        }
        return note;
    }

    public Note write(Note note){
        Optional<Note> optNote = notesInterface.Write(note);

        if(optNote.isEmpty()){
            log.info("La nota esta vacia");
        }
        return optNote.get();
    }


}
