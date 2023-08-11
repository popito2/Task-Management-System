package Models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardTests {


    @Test
    public void testGetName() {
        Board board = new Board("Test Board");
        assertEquals("Test Board", board.getName());
    }

    @Test
    public void testSetName() {
        Board board = new Board("In Name");
        board.setName("New Name");
        assertEquals("New Name", board.getName());
    }


    @Test
    public void testSetNameWithInvalidLength() {
        assertThrows(IllegalArgumentException.class, () ->new Board("Sho"));

    }




    @Test
    public void testAddHistoryEntry() {
        Board board = new Board("Test Board");
        board.addHistoryEntry("Action 1");
        List<String> history = board.getHistory();
        assertTrue(history.contains("Action 1"));
    }

    @Test
    public void testToString() {
        Board board = new Board("Test Board");
        assertEquals("Board: Test Board", board.toString());
    }
}


