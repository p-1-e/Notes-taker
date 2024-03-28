package com.pie.interfaces;

import com.pie.data.Note;

import java.util.List;


public interface NotesInterface {

    String Read();
    List<Note> Search();
    List<Note> Add();
    List<Note> Write();
}
