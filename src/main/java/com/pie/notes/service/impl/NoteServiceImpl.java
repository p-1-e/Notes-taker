package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.exception.noteExeptions.SavingNoteException;
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
    public List<Note> findAll() throws NotesNotFoundException {
        List<Note> notes = noteRepository.findAll();
        if (notes.isEmpty()) {
            throw new NotesNotFoundException();
        }
        return notes;
    }

    @Override
    public List<Note> search(String title) throws NoteNotFoundException {
        var result = noteRepository.findByTitle(title);
        if (result.isEmpty()) {
            throw new NoteNotFoundException(title);
        }
        return result;
    }

    @Override
    public Note save(Note note) throws SavingNoteException {
        try {
            return noteRepository.save(note);
        } catch (PersistenceException e) {
            throw new SavingNoteException(note);
        }
    }

    @Override
    public Note getNote(Long key) throws NoteNotFoundException {
        try {
            return noteRepository.getReferenceById(key);
        } catch (PersistenceException e){
            throw new NoteNotFoundException(key);
        }
    }

    @Override
    public Note remove(Long index) throws DeleteNoteException, NoteNotFoundException {
        var note = getNote(index);
        try {
            noteRepository.delete(note);
            return note;
        } catch (PersistenceException e) {
            throw new DeleteNoteException(note);
        }
    }
}
