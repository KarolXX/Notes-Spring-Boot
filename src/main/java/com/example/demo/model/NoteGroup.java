package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "note_groups")
class NoteGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group") //mappedBy "ego problem"https://www.youtube.com/watch?v=VLlDaIcb3jE
    private Set<Note> notes;

    protected NoteGroup(){
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }
}
