package com.pie.repository;

import com.pie.data.Note;

import java.util.List;
import java.util.Optional;


public interface NotesInterface {

    String read();

    List<Note> search(String term); // this method search in the array of notes by a term 
    // this term can be in the title or in the text of the note 

    List<Note> add(Note note); // this add a note in the text file NoteArray.txt

    Optional<Note> write(Note note);

    List<Note> findAll();// find all the notes that are into the file NoteArray.txt

    Note item(int index); // find the note in the indicated index
}
