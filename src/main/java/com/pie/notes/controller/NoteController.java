package com.pie.notes.controller;

import com.pie.notes.data.Note;
import com.pie.notes.service.NotesService;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NotesService notesService;
    private final Logger log = LogManager.getLogger(NoteController.class);
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
    public ResponseEntity<Note> save( @RequestParam(name = "title") String title, @RequestParam(name = "text") String text) {
        try {
            return ResponseEntity.ok().body(notesService.save(title, text));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
