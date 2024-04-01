package com.pie.repository;

import com.pie.data.Note;

import java.util.List;
import java.util.Optional;


public interface NotesInterface {

    String read();
    List<Note> search(String text);

    List<Note> search(String title);

    List<Note> add(Note note);

    Optional<Note> write(Note note);

    List<Note> findAll();// find all the notes that are into the file NoteArray.txt
    Note item(int index); // find the note in the indicated index
}
