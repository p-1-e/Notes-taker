package com.pie.notes.exception.noteExeptions;

import com.pie.notes.data.Note;

import java.text.MessageFormat;

public class SavingNoteException extends NoteException{
    public SavingNoteException(Note note) {
        super(MessageFormat.format("Can Not save the note: {0}", note.getTitle()));
    }
}
