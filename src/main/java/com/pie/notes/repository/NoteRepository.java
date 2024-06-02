package com.pie.notes.repository;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleAndUser(String title, User user);
    List<Note> findByUser(User user);

    @Modifying
    @Transactional
    @Query("UPDATE Note e SET e.text = :text, e.title = :title WHERE e.id = :id")
    void updateNote(@Param("id") Long id, @Param("title") String title, @Param("text") String text);
}

