package com.pie.interfaces;
import com.pie.data.Note;
import com.pie.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.DIRECTORY;
import static com.pie.utils.NoteConstants.SEPARATOR;


public class NotesImp implements NotesInterface {
    private static final Logger log = LogManager.getLogger();

    @Override
    public String Read() {
        String note = FileUtils.read(DIRECTORY);
        if(note.isEmpty()){
            log.info("No existen notas");
        }else{
            Path path = Paths.get(note);
            try {
                Stream<String> stream = Files.lines(path);
                return stream.map(this::build).toList().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return note;
    }

    @Override
    public List<Note> Search() {
        return null;
    }

    @Override
    public List<Note> Add() {
        return null;
    }

    @Override
    public List<Note> Write() {
        return null;
    }

    private Note build(String text){
        String[] noteArray = text.split(SEPARATOR);
        return new Note(noteArray[0], noteArray[1],noteArray[2],noteArray[3]);

    }
}
