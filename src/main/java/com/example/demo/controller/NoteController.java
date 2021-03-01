package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.model.NoteRepository;

import jdk.dynalink.linker.LinkerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private NoteRepository repository;

    NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/notes")
    ResponseEntity<Note> createNote(@RequestBody @Valid Note note) {
        logger.warn("creating new note");
        var result = repository.save(note);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(path = "/notes", params = {"!sort", "!size", "!page"})
    ResponseEntity<List<Note>> readAllNotes() {
        logger.warn("exposing all notes!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/notes")
    ResponseEntity<List<Note>> readAllNotes(Pageable page) {
        logger.warn("Exposing paged/sorted notes");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/notes/{id}")
    ResponseEntity<?> readNoteById(@PathVariable int id) {
        logger.warn("exposing specified note");
        var result = repository.findById(id);
        if (result != null)
            return ResponseEntity.ok(result);
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable int id, @RequestBody @Valid Note source) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
//        newNote.setId(id);
//        repository.save(newNote);
        repository.findById(id).ifPresent(note -> note.updateFrom(source));
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("notes/{id}")
    public ResponseEntity<?> markAsInvalid(@PathVariable int id) {
        if(!repository.existsById(id))
            return ResponseEntity.notFound().build();
        repository.findById(id).ifPresent(note -> note.setTitle("ATTENTION! It is deprecated " + note.getTitle()));
        return ResponseEntity.noContent().build();
    }
}
