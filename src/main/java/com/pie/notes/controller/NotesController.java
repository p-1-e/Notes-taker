package com.pie.notes.controller;


import com.pie.notes.data.Note;
import com.pie.notes.exception.noteExeptions.DeleteNoteException;
import com.pie.notes.exception.noteExeptions.NoteNotFoundException;
import com.pie.notes.exception.noteExeptions.NotesNotFoundException;
import com.pie.notes.exception.noteExeptions.SavingNoteException;
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
    public ResponseEntity<List<Note>> findAll(){
        try {
            return ResponseEntity.ok(noteService.findAll());
        } catch (NotesNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @PostMapping("/notes/{title}")
    public ResponseEntity<Note> newNote(@PathVariable("title") String title, @RequestParam String text) {
        var note = new Note(title, text);
        try {
            return ResponseEntity.ok(noteService.save(note));
        } catch (SavingNoteException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long index){
        try {
            return  ResponseEntity.ok(noteService.getNote(index));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/notes/{id}")
    public ResponseEntity<Note> actualize(@PathVariable("id") Long id,@RequestBody Note note){
        try {
            return ResponseEntity.ok(noteService.save(note));
        } catch (SavingNoteException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/notes/search-title")
    public ResponseEntity<List<Note>> search(@RequestParam String title){
        try {
            return ResponseEntity.ok(noteService.search(title));
        } catch (NoteNotFoundException | NotesNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @DeleteMapping("/notes")
    public ResponseEntity<Note> delete(@RequestParam Long index){
        try {
            return ResponseEntity.ok(noteService.remove(index));
        } catch (DeleteNoteException|NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
