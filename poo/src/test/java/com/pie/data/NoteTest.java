package com.pie.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    @Test
    public void createNewNote() throws IOException {
        Note note = new Note();
        note.setTitle("Greeting");
        note.setText("hello");

    }
}