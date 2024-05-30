package com.pie.notes.exception;
import java.text.MessageFormat;

public class NoteNotFoundException extends NoteException{
    public NoteNotFoundException(String title) {
        super(MessageFormat.format("Note with name {0} not found", title));
    }

    public NoteNotFoundException(int index) {
        super(MessageFormat.format("Note number {0} was not found", index));
    }
}
