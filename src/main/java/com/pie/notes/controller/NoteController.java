package com.pie.notes.controller;

import com.pie.notes.data.Note;
import com.pie.notes.service.NotesService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NotesService notesService;

    public NoteController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        try{
            return ResponseEntity.ok().body(notesService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
    }

    @PostMapping
    public ResponseEntity<Note> save(@RequestBody String text, String title) {
        Note note = new Note();
        note.setTitle(title);
        note.setText(text);
        note.save();
        try {
            return ResponseEntity.ok().body(notesService.save(note));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
