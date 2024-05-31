package com.pie.notes.data;

import jakarta.persistence.*;

import java.time.LocalDate;


@Table
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private LocalDate date;


    public Note() {
        this.date = LocalDate.now();
    }

    public Note(String title, String text, LocalDate date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Note(String title, String text) {
        this.title = text;
        this.text = text;
        this.date = LocalDate.now();
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

    public Long getId() {
        return id;
    }
}
