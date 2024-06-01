package com.pie.notes.exception.noteExeptions;

import com.pie.notes.data.Note;

import java.text.MessageFormat;

public class DeleteNoteException extends NoteException{
    public DeleteNoteException(Long id) {
        super(MessageFormat.format("Can not delete {0}", id));
    }
}
