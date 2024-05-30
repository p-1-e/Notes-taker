package com.pie.notes.data;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;


@Table
@Entity
public class Note{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Long getId(){
        return id;
    }
}
