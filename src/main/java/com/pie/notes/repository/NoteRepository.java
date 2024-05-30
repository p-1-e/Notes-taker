package com.pie.notes.repository;

import com.pie.notes.data.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
     List<Note> findByTitle(String title);
}

