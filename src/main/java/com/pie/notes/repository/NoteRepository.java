package com.pie.notes.repository;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleAndUser(String title, User user);

    List<Note> findByUser(User user);
}

