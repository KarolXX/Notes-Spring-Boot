package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Note's title must be not null")
    private String title;
    private String note;
    @Embedded
    private Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private NoteGroup group;


    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    public void updateFrom(final Note source) {
        this.title = source.title;
        this.note = source.note;
    }
}
