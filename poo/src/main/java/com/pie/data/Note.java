package com.pie.data;

import com.pie.utils.FileUtils;
import com.pie.utils.NoteUtils;



import java.time.LocalDate;

public class Note {
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
        String[] textNote = NoteUtils.actualize(fileAddress);
        this.title = textNote[0];
        this.text = textNote[1];
    }
    private void save() {
        FileUtils.clean(this.fileAddress);
        FileUtils.write(this.fileAddress, this);
    }

    public String getTitle() {
        actualize();
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        save();
    }

    public String getText() {
        this.actualize();
        return text;
    }

    public void setText(String text) {
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
