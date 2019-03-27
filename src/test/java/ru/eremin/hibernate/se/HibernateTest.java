package ru.eremin.hibernate.se;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.eremin.hibernate.se.entity.Board;
import ru.eremin.hibernate.se.entity.Note;
import ru.eremin.hibernate.se.repository.BoardRepository;
import ru.eremin.hibernate.se.repository.IBoardRepository;
import ru.eremin.hibernate.se.repository.INoteRepository;
import ru.eremin.hibernate.se.repository.NoteRepository;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


/**
 * @autor Eremin Artem on 27.03.2019.
 */

@RunWith(OrderedRunner.class)
public class HibernateTest {

    private static IBoardRepository boardRepository;

    private static INoteRepository noteRepository;

    private static Board board;

    private static Note note;

    @BeforeClass
    public static void before() {
        boardRepository = new BoardRepository();
        noteRepository = new NoteRepository();

        board = new Board();
        board.setName("test");
        board.setDate(new Date());

        note = new Note();
        note.setDescription("testDescription");
        note.setText("testtest");
        note.setBoard(board);
    }

    @Test
    @Order(order = 1)
    public void insertTest() {
        final int noteSize = noteRepository.findAll().size();
        final int boardSize = boardRepository.findAll().size();
        boardRepository.insert(board);
        noteRepository.insert(note);
        final int noteResult = noteRepository.findAll().size() - noteSize;
        final int boardResult = boardRepository.findAll().size() - boardSize;
        assertEquals(noteResult, 1);
        assertEquals(boardResult, 1);
    }

    @Test
    @Order(order = 2)
    public void findTest() {
        assertNotNull(boardRepository.findById(board.getId()));
        assertNotNull(noteRepository.findById(note.getId()));
        assertNotNull(noteRepository.findByBoardId(board));
    }

    @Test
    @Order(order = 3)
    public void updateTest() {
        final Board board1 = boardRepository.findById(board.getId());
        board1.setName("updateBoard");

        final Note note1 = noteRepository.findById(note.getId());
        note1.setDescription("updateDescription");

        boardRepository.update(board1);
        noteRepository.update(note1);

        assertEquals(boardRepository.findById(board.getId()).getName(), "updateBoard");
        assertEquals(noteRepository.findById(note.getId()).getDescription(), "updateDescription");
    }

    @Test
    @Order(order = 4)
    public void deleteTest() {
        final int noteSize = noteRepository.findAll().size();
        final int boardSize = boardRepository.findAll().size();
        noteRepository.delete(note);
        boardRepository.delete(board);
        final int noteResult = noteRepository.findAll().size() - noteSize;
        final int boardResult = boardRepository.findAll().size() - boardSize;
        assertEquals(noteResult, -1);
        assertEquals(boardResult, -1);
    }
}
