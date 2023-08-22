package Commands.Listing;

import Commands.CreateNewBug;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugsTests {
    private ListAllBugs listAllBugs;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        listAllBugs = new ListAllBugs(taskManagementRepository);
    }

    @Test
    public void execute_should_ListAllBugs_When_ValidArgumentsArePassed() {

        Bug bug = taskManagementRepository.createNewBug("Bugbugbugbug", "This is a new bug", Status.ACTIVE, Priority.HIGH, Severity.CRITICAL);
        List<Task> tasks = new ArrayList<>();
        tasks.add(bug);

        List<String> params = new ArrayList<>();
        String result = listAllBugs.execute(params);

        Assertions.assertEquals(tasks.toString(), result);

    }
}
