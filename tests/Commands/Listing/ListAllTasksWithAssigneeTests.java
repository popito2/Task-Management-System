package Commands.Listing;

import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Story;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListAllTasksWithAssigneeTests {

    private TaskManagementRepository taskManagementRepository;

    private ListAllTasksWithAssignee listAllTasksWithAssignee;

    @BeforeEach
    void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();

        listAllTasksWithAssignee = new ListAllTasksWithAssignee(taskManagementRepository);
    }

    @Test
    void testExecuteWithNoTasks() {

        String result = listAllTasksWithAssignee.execute(new ArrayList<>());

        assertEquals("No tasks found.", result);
    }




}
