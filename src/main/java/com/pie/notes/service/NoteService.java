package com.pie.notes.service;
import com.pie.notes.data.Note;
import java.util.List;

public interface NoteService {
    List<Note> findAll();
    List<Note> search(String title);
    Note save(Note note);
    Note actualize(Note note);
    Note getNote(Long key);
    Note remove(Long index);
}