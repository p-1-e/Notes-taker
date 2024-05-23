package com.pie.notes.repository.impl;

import com.pie.notes.data.Note;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesRepositoryImplTest {
    private final String fileAddressTest = "src/test/resources/test.txt";

    @Test
    void save(){
      var note = new Note();
      note.setText("alsdfkjasldfj");
      note.setTitle("laskdflsjf");

    }
}