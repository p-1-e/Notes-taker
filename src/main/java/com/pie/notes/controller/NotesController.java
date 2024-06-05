package com.pie.notes.controller;

import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.service.NoteService;
import com.pie.notes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class NotesController {
    private final NoteService noteService;
    private final UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    private User getUser(Long id){
        List<User> users = userService.findAll();
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.orElse(null);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> findAll(@RequestParam Long userID) {
        User user = getUser(userID);
        if (user == null){
            return ResponseEntity.badRequest().body(List.of());
        }
        try {
            return ResponseEntity.ok(noteService.findAll(user));
        } catch (NotesNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @PostMapping("/note/save")
    public ResponseEntity<Note> newNote(@RequestParam("title") String title, @RequestParam String text, @RequestBody User user) {
        var note = new Note(title, text, user);
        return ResponseEntity.ok(noteService.save(note));
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(noteService.getNote(id));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/notes/{id}")
    public ResponseEntity<Note> update(@PathVariable("id") Long id, @RequestBody Note note) {
        try {
            return ResponseEntity.ok(noteService.update(note));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/notes/search-title")
    public ResponseEntity<List<Note>> search(@RequestParam String title, @RequestParam Long userID) {
        User user = getUser(userID);
        if (user == null){
            return ResponseEntity.badRequest().body(List.of());
        }
        try {
            return ResponseEntity.ok(noteService.search(title, user));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @DeleteMapping("/notes")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        try {
            noteService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (DeleteNoteException e){
            return ResponseEntity.notFound().build();
        }
    }
}
