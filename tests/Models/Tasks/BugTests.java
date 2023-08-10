package Models.Tasks;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BugTests {

    @Test
    public void testConstructorWithValidData() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);

        assertEquals(1, bug.getId());
        assertEquals("Test Bugbug", bug.getTitle());
        assertEquals("This is a new bug", bug.getDescription());
        assertEquals(Status.ACTIVE, bug.getStatus());
        assertEquals(Priority.HIGH, bug.getPriority());
        assertEquals(Severity.MAJOR, bug.getSeverity());
    }

    @Test
    public void testConstructorWithSmallTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bug(1, "Hi", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        });
    }

    @Test
    public void testConstructorWithSmallDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bug(1, "Test Bugbug", "Hi", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        });
    }

    @Test
    public void testGetStepsToReproduce() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        bug.addListOfSteps("Step 1");
        bug.addListOfSteps("Step 2");
        bug.addListOfSteps("Step 3");

        assertEquals(3, bug.getStepsToReproduce().size());
        assertTrue(bug.getStepsToReproduce().contains("Step 1"));
        assertTrue(bug.getStepsToReproduce().contains("Step 2"));
        assertTrue(bug.getStepsToReproduce().contains("Step 3"));
    }

    @Test
    public void testGetPriority() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        assertEquals(Priority.HIGH, bug.getPriority());
    }

    @Test
    public void testGetSeverity() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        assertEquals(Severity.MAJOR, bug.getSeverity());
    }

    @Test
    public void testGetAssignee() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        bug.setAssignee("James");
        assertEquals("James", bug.getAssignee());
    }

    @Test
    public void testAdvanceBugActiveToFixed() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
        assertEquals(Status.FIXED, bug.advanceBug());
    }

    @Test
    public void testAdvanceBugFixedToActive() {
        Bug bug = new Bug(1, "Test Bugbug", "This is a new bug", Status.FIXED, Priority.HIGH, Severity.MAJOR);
        assertEquals(Status.ACTIVE, bug.advanceBug());
    }
}
