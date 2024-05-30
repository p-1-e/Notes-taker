package com.pie.notes.exception;

public class NotesNotFoundException extends NoteException{
    public NotesNotFoundException(String message) {
        super("Videos not found");
    }
}
