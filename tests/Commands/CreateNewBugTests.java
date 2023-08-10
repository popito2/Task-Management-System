package Commands;

import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Bug;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewBugTests {
    private CreateNewBug createNewBug;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        createNewBug = new CreateNewBug(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_StatusIsNotValid(){
        List<String> params = List.of("Bugbugbugbug", "This is a new bug", "Dumb", String.valueOf(Priority.HIGH), String.valueOf(Severity.CRITICAL));

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_PriorityIsNotValid(){
        List<String> params = List.of("Bugbugbugbug", "This is a new bug", String.valueOf(Status.ACTIVE), "dumb", String.valueOf(Severity.CRITICAL));

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_SeverityIsNotValid(){
        List<String> params = List.of("Bugbugbugbug", "This is a new bug", String.valueOf(Status.ACTIVE), String.valueOf(Priority.HIGH), "dumb");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
    }

    @Test
    public void execute_should_CreateNewBug_When_PassedValidInput(){
        List<String> params = List.of("Bugbugbugbug", "This is a new bug", String.valueOf(Status.ACTIVE), String.valueOf(Priority.HIGH), String.valueOf(Severity.CRITICAL));

        createNewBug.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }
}

