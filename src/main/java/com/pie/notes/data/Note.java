package com.pie.notes.data;

import jakarta.persistence.*;
import java.time.LocalDate;



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

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Note() {}

    public Note(Long id, String title, String text, LocalDate date, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.user = user;
    }

    public Note(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
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
