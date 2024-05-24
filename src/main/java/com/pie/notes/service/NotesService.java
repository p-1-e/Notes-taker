package com.pie.notes.service;
import com.pie.notes.data.Note;
import java.util.List;
import java.util.Optional;

public interface NotesService {
    Note save(String title, String text) throws Exception;
    List<Note> findAll() throws Exception;
    Optional<Note> write(Note note);
    List<Note> searchByText(String text) throws Exception;
    List<Note> searchByTitle(String title) throws Exception;
}