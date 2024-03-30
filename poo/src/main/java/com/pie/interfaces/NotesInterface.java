package com.pie.interfaces;

import com.pie.data.Note;

import java.util.List;
import java.util.Optional;


public interface NotesInterface {

    String Read();
    List<Note> Search();
    List<Note> Add();
    Optional<Note> Write(Note note);
}
