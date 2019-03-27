package ru.eremin.hibernate.se.repository;

import ru.eremin.hibernate.se.EntityManagerHandler;
import ru.eremin.hibernate.se.entity.Board;
import ru.eremin.hibernate.se.entity.Note;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

/**
 * @autor Eremin Artem on 26.03.2019.
 */

public class NoteRepository implements INoteRepository {

    private EntityManager em;

    public NoteRepository() {
        this.em = EntityManagerHandler.getEntityManager();
    }

    @Override
    public void insert(final Note note) {
        if (note == null) return;
        em.getTransaction().begin();
        em.persist(note);
        em.getTransaction().commit();
    }

    @Override
    public List<Note> findAll() {
        em.getTransaction().begin();
        final List<Note> notes = em.createQuery("SELECT e FROM Note e", Note.class).getResultList();
        em.getTransaction().commit();
        return notes;
    }

    @Override
    public Note findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        em.getTransaction().begin();
        final Note note = em.createQuery("SELECT e FROM Note e WHERE e.id = :id", Note.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
        em.getTransaction().commit();
        return note;
    }

    @Override
    public List<Note> findByBoardId(final Board board) {
        if (board == null) return Collections.emptyList();
        em.getTransaction().begin();
        final List<Note> notes = em.createQuery("SELECT e FROM Note e WHERE e.board = :board", Note.class)
                .setParameter("board", board)
                .getResultList();
        em.getTransaction().commit();
        return notes;
    }

    @Override
    public void update(final Note note) {
        if (note == null) return;
        em.getTransaction().begin();
        if (em.find(Note.class, note.getId()) == null) return;
        em.merge(note);
        em.getTransaction().commit();
    }

    @Override
    public void delete(final Note note) {
        if (note == null) return;
        em.getTransaction().begin();
        em.remove(note);
        em.getTransaction().commit();
    }
}
