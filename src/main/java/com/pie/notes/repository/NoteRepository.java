package com.pie.notes.repository;

import com.pie.notes.data.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}

