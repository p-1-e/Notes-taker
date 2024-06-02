package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.repository.NoteRepository;
import com.pie.notes.service.NoteService;
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
    public Note update(Note note) throws NoteNotFoundException {
        getNote(note.getId());
        noteRepository.updateNote(note.getId(), note.getTitle(), note.getText());
        return note;
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note getNote(Long id) throws NoteNotFoundException {
        var noteOpt = noteRepository.findById(id);
        if (noteOpt.isEmpty()) {
            throw new NoteNotFoundException(id);
        }
        return noteOpt.get();
    }

    @Override
    public void remove(Long id) throws DeleteNoteException {
        try {
            getNote(id);
            noteRepository.deleteById(id);
        } catch (NoteNotFoundException e) {
            log.info("note {} does not exist", id);
            throw new DeleteNoteException(id);
        }
    }
}
