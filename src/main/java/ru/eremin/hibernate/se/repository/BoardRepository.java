package ru.eremin.hibernate.se.repository;

import ru.eremin.hibernate.se.EntityManagerHandler;
import ru.eremin.hibernate.se.entity.Board;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

public class BoardRepository implements IBoardRepository {

    private EntityManager em;

    public BoardRepository() {
        this.em = EntityManagerHandler.getEntityManager();
    }

    @Override
    public void insert(final Board board) {
        if (board == null) return;
        em.getTransaction().begin();
        em.persist(board);
        em.getTransaction().commit();
    }

    @Override
    public List<Board> findAll() {
        em.getTransaction().begin();
        final List<Board> boards = em.createQuery("SELECT e FROM Board e", Board.class).getResultList();
        em.getTransaction().commit();
        return boards;
    }

    @Override
    public Board findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        em.getTransaction().begin();
        final Board board = em.createQuery("SELECT e FROM Board e WHERE e.id = :id", Board.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
        em.getTransaction().commit();
        return board;
    }

    @Override
    public void update(final Board board) {
        if (board == null) return;
        em.getTransaction().begin();
        if (em.find(Board.class, board.getId()) == null) return;
        em.merge(board);
        em.getTransaction().commit();
    }

    @Override
    public void delete(final Board board) {
        if (board == null) return;
        em.getTransaction().begin();
        em.remove(board);
        em.getTransaction().commit();
    }
}
