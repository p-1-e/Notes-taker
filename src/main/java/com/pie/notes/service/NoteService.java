package com.pie.notes.service;
import com.pie.notes.data.Note;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.exception.noteExeptions.SavingNoteException;

import java.util.List;

public interface NoteService {
    List<Note> findAll() throws NotesNotFoundException;
    List<Note> search(String title) throws NotesNotFoundException, NoteNotFoundException;
    Note save(Note note) throws SavingNoteException;
    Note getNote(Long key) throws NoteNotFoundException;
    Note remove(Long index) throws DeleteNoteException, NoteNotFoundException;
}