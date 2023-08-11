package Commands;

import Core.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddStepsToaBugTest {
    private TaskManagementRepository taskManagementRepository;
    private AddStepsToABug addStepsToABug;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        addStepsToABug = new AddStepsToABug(taskManagementRepository);
    }

    @Test
    public void execute_should_AddSteps_When_ParametersAreValid(){
        CreateNewBug createNewBug = new CreateNewBug(taskManagementRepository);
        List<String> bugParams = List.of("Bugbugbugbug", "This is a new bug", String.valueOf(Status.ACTIVE), String.valueOf(Priority.HIGH), String.valueOf(Severity.CRITICAL));
        createNewBug.execute(bugParams);

        Bug bug = (Bug) taskManagementRepository.findTaskById(1);

        List<String> params = List.of("1", "Hello", "hi");
        String result = addStepsToABug.execute(params);

        assertEquals(bug.getStepsToReproduce().toString(), result);
    }

    @Test
    public void execute_should_ThrowException_When_FirstParameterIsNotNumber(){
        List<String> params = List.of("dumb", "Hello", "Hi");

        Assertions.assertThrows(IllegalArgumentException.class, () -> addStepsToABug.execute(params));
    }
}
