package com.pie.notes.data;

import com.pie.notes.utils.FileUtils;
import com.pie.notes.utils.NoteConstants;
import com.pie.notes.utils.NoteUtils;

import java.text.MessageFormat;
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
        FileUtils.write(this.fileAddress, thisToText());
    }
    public Note(String title, String text, String fileAddress, String date){
        this.title = title;
        this.text = text;
        this.fileAddress = fileAddress;
        this.date = NoteUtils.getDate(date);
    }
    private String thisToText(){
        return this.title + NoteConstants.SEPARATOR + this.text + NoteConstants.SEPARATOR + this.fileAddress + NoteConstants.SEPARATOR + this.date;
    }

    @Override
    public String toString(){
        return MessageFormat.format("title: {0}\ntext: {1}\naddress: {2}\ndate: {3}", this.title, this.text, this.fileAddress, this.date);
    }

    // this method create a new file archive and returns the direction of the archive
    private String createNewFile() {
        return FileUtils.newFile(NoteUtils.getFileAddress());
    }

    public void save() {
        FileUtils.clean(this.fileAddress);
        FileUtils.write(this.fileAddress, NoteUtils.noteToText(this));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        save();
    }

    public String getText() {
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