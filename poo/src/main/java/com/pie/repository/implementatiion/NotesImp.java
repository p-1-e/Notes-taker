package com.pie.repository.implementatiion;

import com.pie.data.Note;
import com.pie.repository.NotesInterface;
import com.pie.utils.FileUtils;
import com.pie.utils.NoteUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.NOTE_ARRAY;
import static com.pie.utils.NoteConstants.SEPARATOR;


public class NotesImp implements NotesInterface {
    private static final Logger log = LogManager.getLogger();

    private static List<Note> notes = new ArrayList<>();

    @Override
    public String read() {
        String note = FileUtils.read(NOTE_ARRAY);
        assert note != null;
        if (note.isEmpty()) {
            log.info("there is not notes");
        } else {

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
    public List<Note> search(String term) { 
        List<Note> notes = findAll();
        return notes.stream().
            filter(note -> note.getTitle().contains(term) || note.getText().contains(term)).toList(); 
    }

    @Override
    public List<Note> add(Note note) {
        String files; 
        // actualize the text file
        if (FileUtils.read(NOTE_ARRAY) == null){
            files = note.getFileAddress(); 
        } else {
            files = FileUtils.read(NOTE_ARRAY) + SEPARATOR + note.getFileAddress();
        }
        FileUtils.clean(NOTE_ARRAY);
        FileUtils.write(NOTE_ARRAY, files);

        // actualize the array in memory
        notes = findAll();
        notes.add(note);
        return notes;
    }

    @Override
    public Optional<Note> write(Note note) {
        String content = NoteUtils.noteToText(note);
        FileUtils.write(note.getFileAddress(), content);
        return Optional.of(note);
    }

    @Override
    public List<Note> findAll() {
        notes = new ArrayList<>();
        if (FileUtils.read(NOTE_ARRAY) == null) {
            return notes;
        }
        String[] directions = Objects.requireNonNull(FileUtils.read(NOTE_ARRAY)).split(SEPARATOR);
        for (String diretion: directions) {
            notes.add(toNote(diretion));
        }
        return notes;
    }

    @Override
    public Note item(int index) {
        return findAll().get(index); 
    }

    private Note toNote(String direccion) {
        String[] noteString = Objects.requireNonNull(FileUtils.read(direccion)).split(SEPARATOR);
        return new Note(noteString[0], noteString[1], noteString[2], noteString[3]);
    }

    private Note build(String text) {
        String[] noteArray = text.split(SEPARATOR);
        return new Note(noteArray[0], noteArray[1], noteArray[2], noteArray[3]);
    }
}
