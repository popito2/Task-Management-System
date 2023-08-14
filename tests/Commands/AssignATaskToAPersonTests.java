package Commands;
import Core.Contracts.TaskManagementRepository;
import Models.Member;
import Models.Tasks.Bug;
import Models.Tasks.Interfaces.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignATaskToAPersonTests {
    private TaskManagementRepository taskManagementRepository;
    private AssignATaskToAPerson assignTaskCommand;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        assignTaskCommand = new AssignATaskToAPerson(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = Arrays.asList("123");

        assertThrows(IllegalArgumentException.class, () -> assignTaskCommand.execute(params));
    }

    @Test
    public void execute_should_ReturnInvalidParametersMessage_When_ArgumentCountDifferentThanExpected() {
        List<String> params = Arrays.asList("123");

        String result = assignTaskCommand.execute(params);

        assertEquals("Invalid number of parameters. Expected: 2", result);
    }



    @Test
    public void execute_should_ThrowException_When_TaskNotFound() {
        int taskId = 123;
        String memberName = "JohnY Dep";
        List<String> parameters = List.of(String.valueOf(taskId), memberName);
        assertThrows(IllegalArgumentException.class, () -> assignTaskCommand.execute(parameters));
    }

    @Test
    public void execute_should_ThrowException_When_MemberNotFound() {

        int taskId = 123;
        String invalidMemberName = "Nonexistent Member";
        List<String> parameters = List.of(String.valueOf(taskId), invalidMemberName);
        assertThrows(IllegalArgumentException.class, () -> assignTaskCommand.execute(parameters));
    }
}

