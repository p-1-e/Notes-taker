package com.pie.notes.exception.noteExeptions;

public class NotesNotFoundException extends NoteException{
    public NotesNotFoundException() {
        super("Notes not found");
    }
}
