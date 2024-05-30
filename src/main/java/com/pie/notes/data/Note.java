package com.pie.notes.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Note{
    private String title;
    private String text;
    private LocalDate date;

    @Id
    @GeneratedValue
    private Long key;

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        this.date = LocalDate.now();
    }

    public Note(String title, String text, LocalDate date){
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Note() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }
}
