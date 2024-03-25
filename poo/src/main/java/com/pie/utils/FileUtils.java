package com.pie.utils;

import com.pie.data.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private FileUtils(){} // avoids than someone initialize the class
    public static String CreateTextFile(String fileName) {
        try {
            Path path = Path.of(fileName);
            Files.createFile(path); // this creates the new file
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clean(String fileAddress) { // this method delete all the text in the file
        try (FileWriter writer = new FileWriter(fileAddress, false)) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String fileAddress, Note note){
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileAddress), StandardCharsets.UTF_8)) {
            writer.write(NoteUtils.noteToText(note)); // write the new text in the file
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }



}
