package com.pie.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static java.lang.StringTemplate.STR;

public class Note {
    public final Logger logger = LogManager.getLogger(Note.class);
    private String title;
    private String text;
    private final String fileAddress;
    private final LocalDate date;
    private final String route = "./scr/main/resources/grade/";

    /**
     * this is the constructor of the note class
     */
    public Note() {
        this.date = LocalDate.now(); // this line saves the date of the creation of the Note
        this.fileAddress = this.createNewFile();
    }

    // this method create a new file archive and returns the direction of the archive
    private String createNewFile() {
        try {
            // this put an address whit new aleatory name
            String _fileAddress = STR."\{this.route}\{UUID.randomUUID()}.txt";
            Files.createFile(Path.of(_fileAddress)); // this creates the new file
            logger.info(STR."the new note is in: \{this.fileAddress}");
            return _fileAddress;
        } catch (IOException e) {
            logger.error("the creation of new note not work");
            return null;
        }
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
        this.text = note[0];
        this.title = note[1];
    }

    private void save() throws IOException {
        cleanFile();
        String _text = STR."\{this.title},\{this.text},\{this.fileAddress},\{this.date}";
        try(BufferedWriter writer = Files.newBufferedWriter(Path.of(this.fileAddress))) {
            writer.write(_text); // write the new text in the file
        }catch (IOException e){
            logger.error("cant writing");
        }
    }

    private void cleanFile() throws IOException {
        try(FileWriter writer = new FileWriter(this.fileAddress, false)){
            this.logger.info("the file was not clean");
        }
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
        this.save();
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public LocalDate getDate() {
        return date;
    }
}
