package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.repository.NotesRepository;
import com.pie.notes.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;

    public NotesServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public Note save(String title, String text) throws Exception {
        Note note = new Note();
        note.setTitle(title);
        note.setText(text);
        var result = notesRepository.save(note);
        if (result == null){
            throw new Exception("Can't save");
        }
        return result;
    }

    @Override
    public List<Note> findAll() throws Exception {
        var notes = notesRepository.findAll();
        if (notes.isEmpty()) {
            throw new Exception("not found any note");
        }
        return notes;
    }

    @Override
    public Optional<Note> write(Note note) {
        return notesRepository.write(note);
    }

    @Override
    public List<Note> searchByText(String text) throws Exception {
        var notes = notesRepository.searchByText(text);
        if (notes.isEmpty()) {
            throw new Exception("not found any note");
        }
        return notes;
    }

    @Override
    public List<Note> searchByTitle(String title) throws Exception {
        var notes = notesRepository.searchByTitle(title);
        if (notes.isEmpty()) {
            throw new Exception("not found any note");
        }
        return notes;
    }
}
