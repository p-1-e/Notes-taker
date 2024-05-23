package com.pie.notes.utils;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.UUID;
import com.pie.notes.data.Note;

import static com.pie.notes.utils.NoteConstants.SEPARATOR;


public class NoteUtils {

    public static String noteToText(Note note) {
        // return STR."\{note.getTitle()}" + NoteConstants.SEPARATOR + "\{note.getText()},\{note.getDate()},\{note.getFileAddress()}";
        return note.getTitle() + SEPARATOR + note.getText() + SEPARATOR + note.getDate() + SEPARATOR + note.getFileAddress();
    }

    // this method put an aleatory name to the file address
    public static String getFileAddress() {
        return MessageFormat.format("{0}{1}.txt", NoteConstants.DIRECTORY, UUID.randomUUID().toString());
    }


    private static String[] build(String s) {
        return s.split(SEPARATOR);
    }

    public static LocalDate getDate(String date) {
        String[] arrayDate = date.split("-");
        return LocalDate.of(Integer.parseInt(arrayDate[0]), Integer.parseInt(arrayDate[1]), Integer.parseInt(arrayDate[2]));
    }
}