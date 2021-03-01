package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
interface SqlNoteRepository extends NoteRepository, JpaRepository<Note, Integer> {
    //@Param("paramName") is used to be able to use parameter name that way: :nameParam
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from notes where id=:id")
    boolean existsById(@Param("id") Integer id);

    //Spring it can also return the selected record as a java serialized object(Optional<Note>)
    @Override
    @Query(nativeQuery = true, value = "select * from notes where id=:id")
    Optional<Note> findById(@Param("id") Integer id);
}
