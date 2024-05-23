package com.pie.notes.repository;

import com.pie.notes.data.Note;
import java.util.List;
import java.util.Optional;

public interface NotesRepository {

    List<Note> searchByTitle(String title);

    List<Note> searchByText(String text);

    Note save(Note note);

    Optional<Note> write(Note note);

    Note item(int index);

    List<Note> findAll();
}
