package com.pie.notes.service;
import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;

import java.util.List;

public interface NoteService {
    List<Note> findAll(User user) throws NotesNotFoundException;
    List<Note> search(String title, User user) throws NoteNotFoundException;
    Note save(Note note);
    Note getNote(Long id) throws NoteNotFoundException;
    void remove(Long id) throws DeleteNoteException;
    Note update(Note note) throws NoteNotFoundException;
}