package com.pie.notes.service;

import com.pie.notes.data.Note;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.exception.noteExeptions.SavingNoteException;
import com.pie.notes.repository.NoteRepository;
import jakarta.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.pie.notes.data.User;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private static final Logger log = LogManager.getLogger(NoteServiceImpl.class);
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll(User user) throws NotesNotFoundException {
        List<Note> notes = noteRepository.findByUser(user);
        if (notes.isEmpty()) {
            throw new NotesNotFoundException();
        }
        return notes;
    }

    @Override
    public List<Note> search(String title, User user) throws NoteNotFoundException {
        var result = noteRepository.findByTitleAndUser(title, user);
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
    public Note getNote(Long id) throws NoteNotFoundException {
        try {
            return noteRepository.getReferenceById(id);
        } catch (PersistenceException e){
            throw new NoteNotFoundException(id);
        }
    }

    @Override
    public Note remove(Long id) throws DeleteNoteException, NoteNotFoundException {
        var note = getNote(id);
        try {
            noteRepository.delete(note);
            return note;
        } catch (PersistenceException e) {
            throw new DeleteNoteException(note);
        }
    }
}
