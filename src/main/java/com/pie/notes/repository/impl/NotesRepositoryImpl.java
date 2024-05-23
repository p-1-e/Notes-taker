package com.pie.notes.repository.impl;

import com.pie.notes.data.Note;
import com.pie.notes.repository.NotesRepository;
import com.pie.notes.utils.FileUtils;
import com.pie.notes.utils.NoteUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pie.notes.utils.NoteConstants.NOTE_ARRAY;
import static com.pie.notes.utils.NoteConstants.SEPARATOR;

@Repository
public class NotesRepositoryImpl implements NotesRepository {
    private static final Logger log = LogManager.getLogger();

    private static List<Note> notes = new ArrayList<>();

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
    public Note save(Note note) {
        findAll().add(note);
        String direction = MessageFormat.format("{0}{1}", note.getFileAddress(), SEPARATOR);
        FileUtils.write(NOTE_ARRAY, direction);
        return note;
    }

    @Override
    public Optional<Note> write(Note note) {
        String content = NoteUtils.noteToText(note);
        FileUtils.write(note.getFileAddress(), content);
        return Optional.of(note);
    }

    @Override
    public Note item(int index) {
        if (findAll().get(index) == null) {
            return null;
        }
        return findAll().get(index);
    }

    @Override
    public List<Note> findAll() {
        notes = List.of();
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