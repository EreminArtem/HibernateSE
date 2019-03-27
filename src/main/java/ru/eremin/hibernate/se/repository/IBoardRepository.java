package ru.eremin.hibernate.se.repository;

import ru.eremin.hibernate.se.entity.Board;

import java.util.List;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

public interface IBoardRepository {

    void insert(Board board);

    List<Board> findAll();

    Board findById(String id);

    void update(Board board);

    void delete(Board board);

}
