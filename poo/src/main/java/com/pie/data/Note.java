package com.pie.data;

import com.pie.utils.FileUtils;
import com.pie.utils.NoteUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Stream;

public class Note {
    public final Logger logger = LogManager.getLogger(Note.class);
    private String title;
    private String text;
    private final String fileAddress;
    private final LocalDate date;
    /**
     * this is the constructor of the note class
     */
    public Note() {
        this.date = LocalDate.now(); // this line saves the date of the creation of the Note
        this.fileAddress = this.createNewFile();
    }

    // this method create a new file archive and returns the direction of the archive
    private String createNewFile() {
        return FileUtils.CreateTextFile(NoteUtils.getFileAddress());
    }

    private void actualize() {
        Path path = Path.of(this.fileAddress);
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(this::build);
        } catch (IOException e) {
            logger.error("The reading of the path don't work");
        }
    }

    private void build(String text) {
        String[] note = text.split(",");
        this.title = note[0]; // title
        this.text = note[1]; // text
    }

    private void save() {
        FileUtils.clean(this.fileAddress);
        FileUtils.write(this.fileAddress, this);
    }

    public String getTitle() {
        actualize();
        return title;
    }

    public void setTitle(String title) throws IOException {
        this.title = title;
        save();
    }

    public String getText() {
        this.actualize();
        return text;
    }

    public void setText(String text) throws IOException {
        this.text = text;
        save();
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public LocalDate getDate() {
        return date;
    }
}
