package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.repository.UserRepository;
import com.pie.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void save() {
        var user = userRepository.save(new User("santiago", "1235"));
        var note = new Note("title", "text", user);
        var result = noteService.save(note);
        assertEquals(note.getTitle(), result.getTitle());

    }

    @Test
    void findAll() throws NotesNotFoundException {
        var user = userRepository.save(new User("santiago", "1235"));
        var notes = List.of(
                new Note("title", "text", user),
                new Note("hhh", "text", user),
                new Note("yy", "text", user)
        );

        notes.forEach(noteService::save);


        var results = noteService.findAll(user);

        assertEquals(notes.size() , results.size());


    }
}