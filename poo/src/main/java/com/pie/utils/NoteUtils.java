package com.pie.utils;

import com.pie.data.Note;

import java.util.UUID;

public class NoteUtils {

    public static String noteToText(Note note){
        return STR."\{note.getTitle()},\{note.getText()},\{note.getDate()},\{note.getFileAddress()}";
    }

    // this method put an aleatory name to the file address
    public static String getFileAddress(){
        return STR."\{NoteConstants.DIRECTORY}\{UUID.randomUUID().toString()}.txt";
    }
}
