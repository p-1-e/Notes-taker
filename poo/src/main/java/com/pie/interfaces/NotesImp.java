package com.pie.interfaces;
import com.pie.data.Note;
import com.pie.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.DIRECTORY;
import static com.pie.utils.NoteConstants.NOTE_ARRAY;
import static com.pie.utils.NoteConstants.SEPARATOR;


public class NotesImp implements NotesInterface {
    private static final Logger log = LogManager.getLogger();

    private List<Note> notes;
    public NotesImp() {
        this.notes = new ArrayList<>();
        //this.notes.add(new Note("Note","Prueba2","poo/src/main/resources/Nota.txt","2024-03-30"));
    }

    @Override
    public String Read() {
        String note = FileUtils.read(NOTE_ARRAY);
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
    public Optional<Note> Write(Note note) {
      /*  Path path = Paths.get(NOTE_ARRAY);

        Charset charset = StandardCharsets.UTF_8;
        String content = MessageFormat.format("{0},{1},{2},{3}", note.getTitle(), note.getText(),note.getFileAddress(),note.getDate());
        try(BufferedWriter writer = Files.newBufferedWriter(path, charset)){
            writer.write(content);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(note);

       */
    boolean result = notes.add(note);

    if(!result){
        return Optional.empty();
    }

    return Optional.of(note);
    }

    private Note build(String text){
        String[] noteArray = text.split("#");
        return new Note(noteArray[0], noteArray[1],noteArray[2],noteArray[3]);

    }
}
