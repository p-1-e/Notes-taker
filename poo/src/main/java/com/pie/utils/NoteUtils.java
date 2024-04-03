package com.pie.utils;

import com.pie.data.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.SEPARATOR;

public class NoteUtils {

    public static String noteToText(Note note) {
        // return STR."\{note.getTitle()}" + NoteConstants.SEPARATOR + "\{note.getText()},\{note.getDate()},\{note.getFileAddress()}";
        return note.getTitle() + SEPARATOR + note.getText() + SEPARATOR + note.getDate() + SEPARATOR + note.getFileAddress();
    }

    // this method put an aleatory name to the file address
    public static String getFileAddress() {
        return STR."\{NoteConstants.DIRECTORY}\{UUID.randomUUID().toString()}.txt";
    }


    private static String[] build(String s) {
        return s.split(SEPARATOR);
    }

    public static LocalDate getDate(String date) {
        String[] arrayDate = date.split("-");
        return LocalDate.of(Integer.parseInt(arrayDate[0]), Integer.parseInt(arrayDate[1]), Integer.parseInt(arrayDate[2]));
    }
}
