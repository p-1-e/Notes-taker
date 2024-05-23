package com.pie.notes.repository.impl;

import com.pie.notes.data.Note;
import com.pie.notes.repository.NotesRepository;
import com.pie.notes.utils.FileUtils;
import com.pie.notes.utils.NoteUtils;
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
import java.util.stream.Collectors;

import static com.pie.notes.utils.NoteConstants.NOTE_ARRAY;
import static com.pie.notes.utils.NoteConstants.SEPARATOR;

public class NotesImp implements NotesRepository {
    private static final Logger log = LogManager.getLogger();

    private static List<Note> notes = new ArrayList<>();

    @Override
    public String Read() {
        String note = FileUtils.read(NOTE_ARRAY);
        if (note.isEmpty()) {
            log.info("No existen notas");
        } else {

            Path path = Paths.get(note);
            try {
                // todo: fix it
                Stream<String> stream = Files.lines(path);
                return stream.map(this::build).toList().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return note;
    }

    @Override
    public List<Note> searchByTitle(String title) {
        List<Note> foundNotes = new ArrayList<>();
        List<Note> allNotes = this.findAll();
        for (Note note : allNotes) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                foundNotes.add(note);
            }
        }
        return foundNotes;
    }

    @Override
    public List<Note> searchByText(String text) {
        List<Note> allNotes = this.findAll();
        return allNotes.stream()
                .filter(note -> note.getText().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList()); // Filtrar las notas por texto de búsqueda y devolver la lista resultante
    }

    @Override
    public List<Note> Add(Note note) {
        // actualize the file text
        String files;
        if (FileUtils.read(NOTE_ARRAY) != null)
            files = FileUtils.read(NOTE_ARRAY) + SEPARATOR + note.getFileAddress();
        else
            files = note.getFileAddress();
        FileUtils.clean(NOTE_ARRAY);
        FileUtils.write(NOTE_ARRAY, files);

        // actualize in ram
        notes = findAll();
        notes.add(note);
        return notes;
    }

    @Override
    public Optional<Note> Write(Note note) {
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
        for (String direction : directions) {
            notes.add(toNote(direction));
        }
        return notes;
    }

    private Note toNote(String direction) {
        String[] noteString = FileUtils.read(direction).split(SEPARATOR);
        return new Note(noteString[0], noteString[1], noteString[2], noteString[3]);
    }

    private Note build(String text) {
        String[] noteArray = text.split(SEPARATOR);
        return new Note(noteArray[0], noteArray[1], noteArray[2], noteArray[3]);
    }
}