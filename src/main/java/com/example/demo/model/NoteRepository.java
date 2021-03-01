package com.example.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    Note save(Note entity);

    Optional<Note> findById(Integer id);

    List<Note> findAll();

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Page<Note> findAll(Pageable page);
}
