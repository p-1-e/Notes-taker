package com.pie.notes.controller;


import com.pie.notes.data.Note;
import com.pie.notes.data.User;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> findAll(@RequestBody User user) {
        try {
            return ResponseEntity.ok(noteService.findAll(user));
        } catch (NotesNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @PostMapping("/note/{title}")
    public ResponseEntity<Note> newNote(@PathVariable("title") String title, @RequestParam String text, @RequestBody User user) {
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
    public ResponseEntity<Note> actualize(@PathVariable("id") Long id, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.save(note));
    }

    @GetMapping("/notes/search-title")
    public ResponseEntity<List<Note>> search(@RequestParam String title, @RequestBody User user) {
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
