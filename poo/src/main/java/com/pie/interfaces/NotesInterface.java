package com.pie.interfaces;

import com.pie.data.Note;

import java.util.List;
import java.util.Optional;


public interface NotesInterface {

    String Read();

    List<Note> searchByTitle(String title);

    List<Note> searchByText(String text);

    List<Note> Add(Note note);

    Optional<Note> Write(Note note);

    List<Note> findAll();// recuperar todas las notas que esten en l
}
