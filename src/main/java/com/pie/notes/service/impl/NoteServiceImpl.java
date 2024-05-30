package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.repository.NoteRepository;
import com.pie.notes.service.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> search(String title) {
        return noteRepository.findByTitle(title);
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note actualize(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note getNote(Long key) {
        return noteRepository.getReferenceById(key);
    }

    @Override
    public Note remove(Long index) {
        Note note = getNote(index);
        noteRepository.delete(note);
        return note;
    }
}
