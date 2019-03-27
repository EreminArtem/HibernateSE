package ru.eremin.hibernate.se.repository;

import ru.eremin.hibernate.se.entity.Board;
import ru.eremin.hibernate.se.entity.Note;

import java.util.List;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

public interface INoteRepository {

    void insert(Note note);

    List<Note> findAll();

    Note findById(String id);

    List<Note> findByBoardId(Board board);

    void update(Note note);

    void delete(Note note);


}
