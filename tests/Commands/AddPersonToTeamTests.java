package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Member;
import Models.interfaces.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddPersonToTeamTests {
    private TaskManagementRepository taskManagementRepository;
    private AddPersonToTeam addPersonToTeam;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        addPersonToTeam = new AddPersonToTeam(taskManagementRepository);
    }

    @Test
    public void execute_should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[1]);;

        assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(params));
    }

    @Test
    public void execute_should_AddPersonToTeam_When_InputIsValid(){
        CreateNewPerson createNewPerson = new CreateNewPerson(taskManagementRepository);
        CreateNewTeam createNewTeam = new CreateNewTeam(taskManagementRepository);
        List<String> person = List.of("Baba Danchi");
        List<String> team = List.of("Banditi");

        createNewPerson.execute(person);
        createNewTeam.execute(team);

        List<String> params = List.of("Banditi", "Baba Danchi");
        String result = addPersonToTeam.execute(params);

        assertEquals("Baba Danchi has been added to team Banditi", result);
    }
}
