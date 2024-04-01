package com.pie.utils;

import com.pie.data.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.SEPARATOR;

public class NoteUtils {

    public static String noteToText(Note note) {
        return note.getTitle() + SEPARATOR + note.getText() + SEPARATOR + note.getFileAddress() + SEPARATOR + note.getDate();
    }

    // this method put an aleatory name to the file address
    public static String getFileAddress() {
        return STR."\{NoteConstants.DIRECTORY}\{UUID.randomUUID().toString()}.txt";
    }
    public static Note textToNote(String direccion) {
        String[] noteString = Objects.requireNonNull(FileUtils.read(direccion)).split(SEPARATOR);
        return new Note(noteString[0], noteString[1], noteString[2], noteString[3]);
    }

    public static String[] actualize(String fileAddress) {
        Path path = Path.of(fileAddress);
        try (Stream<String> reading = Files.lines(path)) {
            String line = reading.findFirst().orElse(null);
            if (line != null) {
                return NoteUtils.build(line);
            }
            return new String[]{"", ""};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] build(String s) {
        return s.split(SEPARATOR);
    }

    public static LocalDate getDate(String date) {
        String[] arrayDate = date.split("-");
        return LocalDate.of(Integer.parseInt(arrayDate[0]), Integer.parseInt(arrayDate[1]), Integer.parseInt(arrayDate[2]));
    }
}
