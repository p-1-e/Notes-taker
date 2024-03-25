package com.pie.utils;

import com.pie.data.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

public class NoteUtils {

    public static String noteToText(Note note){
        return STR."\{note.getTitle()},\{note.getText()},\{note.getDate()},\{note.getFileAddress()}";
    }

    // this method put an aleatory name to the file address
    public static String getFileAddress(){
        return STR."\{NoteConstants.DIRECTORY}\{UUID.randomUUID().toString()}.txt";
    }
    public static String[] actualize(String fileAddress){
        Path path = Path.of(fileAddress);
        try (Stream<String> reading = Files.lines(path)){
            String line = reading.findFirst().orElse(null);
            if (line != null) {
                return NoteUtils.build(line);
            }
            throw new NullPointerException("the archive was void");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static String[] build(String s) {
        return s.split(",");
    }
}
