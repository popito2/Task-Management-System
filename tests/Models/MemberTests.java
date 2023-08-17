package Models;

import Models.Tasks.Enums.Status;
import Models.Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTests {
    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member("JohnDoe");
    }

    @Test
    public void testConstructorWithValidName() {
        Member member = new Member("JohnDoe");
        assertEquals("JohnDoe", member.getName());
    }

    @Test
    public void testConstructorWithNameTooShort() {
        assertThrows(IllegalArgumentException.class, () -> new Member("Tom"));
    }

    @Test
    public void testConstructorWithNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Member("VeryLongNameHere123"));
    }

    @Test
    public void testValidName() {
        assertEquals("JohnDoe", member.getName());
    }


    //For question
    @Test
    public void testAddTask() {
        Task task = initializeTask();
        member.addTask(task);
        assertTrue(member.getTasks().contains(task));
    }

    @Test
    public void testRemoveTask() {
        Task task = initializeTask();
        member.addTask(task);
        member.removeTask(task);
        assertFalse(member.getTasks().contains(task));
    }

    @Test
    public void testRemoveNonExistentTask() {
        Task task = initializeTask();
        member.removeTask(task);
        assertFalse(member.getTasks().contains(task));
    }

    @Test
    public void testAddHistory() {
        member.addHistory("History 1");
        List<String> historyChanges = member.getHistoryChanges();
        assertTrue(historyChanges.contains("History 1"));
    }

    @Test
    public void testAddMultipleHistory() {
        member.addHistory("History 1");
        member.addHistory("History 2");
        List<String> historyChanges = member.getHistoryChanges();
        assertTrue(historyChanges.contains("History 1"));
        assertTrue(historyChanges.contains("History 2"));
    }

    @Test
    public void testEmptyTaskList() {
        assertTrue(member.getTasks().isEmpty());
    }

    @Test
    public void testEmptyHistoryChanges() {
        assertTrue(member.getHistoryChanges().isEmpty());
    }

    public static Task initializeTask(){
        return new Task(1, "HelloHello", "This is a description", Status.ACTIVE);
    }
}
