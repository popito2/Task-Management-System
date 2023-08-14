package Core;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Task;
import Models.interfaces.Team;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TaskManagementRepositoryTests {
    public class TaskManagementRepositoryTest {
        private TaskManagementRepository repository;

        @Before
        public void setUp() {
            repository = new TaskManagementRepository();
        }

        @Test
        public void testCreateNewTeam() {
            Team team = repository.createNewTeam("TeamA");
            assertNotNull(team);
            assertEquals("TeamA", team.getName());

            // Try to create a team with the same name
            try {
                repository.createNewTeam("TeamA");
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException e) {
                assertEquals(TaskManagementRepository.THE_TEAM_ALREADY_EXISTS, e.getMessage());
            }
        }

        @Test
        public void testCreateNewPerson() {
            Models.interfaces.Member member = repository.createNewPerson("John");
            assertNotNull(member);
            assertEquals("John", member.getName());

            // Try to find the created member
            Models.interfaces.Member foundMember = repository.findMemberByName("John");
            assertNotNull(foundMember);
            assertEquals(member, foundMember);

            // Try to find a non-existing member
            try {
                repository.findMemberByName("Alice");
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException e) {
                assertEquals("Member not found", e.getMessage());
            }
        }

        // You can similarly write tests for createNewBoard, createNewBug, createNewFeedback, createNewStory,
        // and other methods in the repository.

        @Test
        public void testFindTeamByName() {
            repository.createNewTeam("TeamA");
            repository.createNewTeam("TeamB");

            Team teamA = repository.findTeamByName("TeamA");
            assertNotNull(teamA);
            assertEquals("TeamA", teamA.getName());

            Team teamB = repository.findTeamByName("TeamB");
            assertNotNull(teamB);
            assertEquals("TeamB", teamB.getName());

            // Try to find a non-existing team
            try {
                repository.findTeamByName("TeamC");
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException e) {
                assertEquals("Team not found.", e.getMessage());
            }
        }

        // You can similarly write tests for other find methods in the repository.

        @Test
        public void testFindTaskById() {
            Task task1 = repository.createNewBug("Bug 1", "Description", Status.OPEN, Priority.HIGH, Severity.MAJOR);
            Task task2 = repository.createNewStory("Story 1", "Description", Priority.MEDIUM, Size.LARGE, Status.IN_PROGRESS);

            Task foundTask1 = repository.findTaskById(task1.getId());
            assertNotNull(foundTask1);
            assertEquals(task1, foundTask1);

            Task foundTask2 = repository.findTaskById(task2.getId());
            assertNotNull(foundTask2);
            assertEquals(task2, foundTask2);

            // Try to find a non-existing task
            try {
                repository.findTaskById(-1);
                fail("Expected an IllegalArgumentException to be thrown");
            } catch (IllegalArgumentException e) {
                assertEquals("Task not found.", e.getMessage());
            }
        }
    }

}
