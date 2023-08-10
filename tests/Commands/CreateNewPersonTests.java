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

public class CreateNewPersonTests {
    private CreateNewPerson createNewPerson;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        createNewPerson = new CreateNewPerson(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);

        assertThrows(IllegalArgumentException.class, () -> createNewPerson.execute(params));
    }

    @Test
    public void execute_should_CreateNewPerson_When_PassedValidInput(){
        List<String> params = List.of("Bat Georgi");

        createNewPerson.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getMembers().size());
    }
}
