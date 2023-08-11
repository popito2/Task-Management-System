package Commands;

import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewStoryTests {
    private CreateNewStory createNewStory;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        createNewStory = new CreateNewStory(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);

        assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_StatusIsNotValid(){
        List<String> params = List.of("StoryStory", "This is a new Story",
                String.valueOf(Priority.MEDIUM),String.valueOf(Size.MEDIUM), "String.valueOf(Status.IN_PROGRESS)");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_PriorityIsNotValid(){
        List<String> params = List.of("StoryStory", "This is a new Story",
                "String.valueOf(Priority.MEDIUM)",String.valueOf(Size.MEDIUM), String.valueOf(Status.IN_PROGRESS));

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(params));
    }

    @Test
    public void execute_should_ThrowException_When_SizeIsNotValid(){
        List<String> params = List.of("StoryStory", "This is a new Story",
                String.valueOf(Priority.MEDIUM),"String.valueOf(Size.MEDIUM)", String.valueOf(Status.IN_PROGRESS));

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(params));
    }

    @Test
    public void execute_should_CreateNewStory_When_PassedValidInput(){
        List<String> params = List.of("StoryStory", "This is a new Story",
                String.valueOf(Priority.MEDIUM),String.valueOf(Size.MEDIUM), String.valueOf(Status.IN_PROGRESS));

        createNewStory.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }
}
