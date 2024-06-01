package com.pie.notes.service.impl;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.repository.UserRepository;
import com.pie.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;
    private User user;
    private List<Note> testingNotes;


    @Test
    void save() {
        user = userRepository.save(new User("santiago", "1235"));
        var note = new Note("title", "text", user);
        var result = noteService.save(note);
        assertEquals(note.getTitle(), result.getTitle());
    }

    private void settingTestingNotes() {
        user = userRepository.save(new User("santiago", "1235"));
        testingNotes = List.of(
                new Note("qqq", "fff", user),
                new Note("eee", "ttt", user),
                new Note("yyy", "www", user)
        );
    }

    private boolean listNotesEquals(List<Note> x, List<Note> y) {
        boolean equals = true;
        for (int i = 0; i < x.size(); i++) {
            equals = x.get(i).getTitle().equals(y.get(i).getTitle())
                    && x.get(i).getText().equals(y.get(i).getText());
        }
        return equals;
    }

    @Test
    void findAll() throws NotesNotFoundException {
        settingTestingNotes();
        testingNotes.forEach(noteService::save);
        var foundedNotes = noteService.findAll(user);
        boolean equals = listNotesEquals(testingNotes, foundedNotes);
        assertTrue(equals);
    }

    @Test
    void search() throws NoteNotFoundException {
        settingTestingNotes();
        var expectedTitle = "yyy";
        List<Note> target = testingNotes.stream().filter(note -> note.getTitle().equals(expectedTitle)).toList();
        testingNotes.forEach(noteService::save);
        List<Note> searchedNotes = noteService.search(expectedTitle, user);
        assertTrue(listNotesEquals(target, searchedNotes));
    }

    @Test
    void getNote() throws NoteNotFoundException {
        settingTestingNotes();
        testingNotes.forEach(noteService::save);
        Note expected = testingNotes.get(2);
        Long id = expected.getId();
        Note foundedNote = noteService.getNote(id);
        assertEquals(expected.getId(), foundedNote.getId());
    }

    @Test void remove() throws DeleteNoteException, NotesNotFoundException {
        settingTestingNotes();
        testingNotes.forEach(noteService::save);
        Long id = testingNotes.get(0).getId();
        noteService.remove(id);
        var notes = noteService.findAll(user);
        assertEquals(testingNotes.size() - 1 , notes.size());
    }

}