package Commands;

import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewFeedbackTests {
    private CreateNewFeedback createNewFeedback;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        createNewFeedback = new CreateNewFeedback(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> createNewFeedback.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_StatusIsNotValid(){
        List<String> params = List.of("Bugbugbugbug", "This is a new feedback", "Dumb","10");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewFeedback.execute(params));
    }



    @Test
    public void execute_should_CreateNewBug_When_PassedValidInput(){
        List<String> params = List.of("Bugbugbugbug", "This is a new feedback", String.valueOf(Status.ACTIVE),"10");
        createNewFeedback.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }

    @Test
    public void execute_should_ThrowException_When_RatingIsNotValid(){
        List<String> params = List.of("Bugbugbugbug", "This is a new feedback", String.valueOf(Status.ACTIVE),"dumb");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewFeedback.execute(params));
    }


    @Test
    public void execute_InvalidArgumentCount_ThrowsException() {
        List<String> parameters = Arrays.asList("Feedback description", "Feedback title", "Open");
        assertThrows(IllegalArgumentException.class, () -> createNewFeedback.execute(parameters));
    }

}

