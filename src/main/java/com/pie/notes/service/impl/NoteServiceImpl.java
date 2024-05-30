package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.repository.NoteRepository;
import com.pie.notes.service.NoteService;
import jakarta.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private static final Logger log = LogManager.getLogger(NoteServiceImpl.class);
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
        var result = noteRepository.findByTitle(title);
        if (result.isEmpty()) {
            //Todo: raise an Exception
        }
        return result;
    }

    @Override
    public Note save(Note note) {
        try {
            return noteRepository.save(note);
        } catch (PersistenceException e) {
            log.info("Can't save: {}, exception: {}", note, e.getMessage());
            // Todo: raise an Exception
        }
    }

    @Override
    public Note actualize(Note note) {
        try {
            return noteRepository.save(note);
        } catch (PersistenceException e) {
            log.info("Can't actualize: {}, exception: {}", note, e.getMessage());
            // Todo: raise an Exception
        }
    }

    @Override
    public Note getNote(Long key) {
        return noteRepository.getReferenceById(key);
    }

    @Override
    public Note remove(Long index) {
        var note = getNote(index);
        try {
            noteRepository.delete(note);
        } catch (PersistenceException e) {
            log.error("Can't remove {}, error: {}", note, e.getMessage());
            // Todo: raise an exception
        }
    }
}
