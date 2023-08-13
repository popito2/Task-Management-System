package Commands.Listing;

import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Task;
import Models.Tasks.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListAllTasksTests {
    private TaskManagementRepository taskManagementRepository;

    private List<Task> tasks;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new Core.TaskManagementRepository();
        tasks = new ArrayList<>(taskManagementRepository.getTasks());
    }

    @Test
    void testNoStories() {
        ListAllTasks listAllTasks = new ListAllTasks(taskManagementRepository);
        tasks.removeIf(task -> task instanceof Story);
        String result = listAllTasks.execute(new ArrayList<>());
        assertEquals("There are no tasks.", result);
    }
}
