package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTheTeamMembersTests {

    private TaskManagementRepository taskManagementRepository;
    private ShowAllTheTeamMembers showAllTheTeamMembers;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showAllTheTeamMembers = new ShowAllTheTeamMembers(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> showAllTheTeamMembers.execute(params));
    }

    @Test
    public void should_ShowAllTeamMembers_When_ArgumentsAreValid(){
        CreateNewTeam createNewTeam = new CreateNewTeam(taskManagementRepository);
        CreateNewPerson createNewPerson = new CreateNewPerson(taskManagementRepository);
        AddPersonToTeam addPersonToTeam = new AddPersonToTeam(taskManagementRepository);

        List<String> team = List.of("Banditi");
        List<String> member = List.of("James");
        List<String> add = List.of("Banditi", "James");

        createNewTeam.execute(team);
        createNewPerson.execute(member);
        addPersonToTeam.execute(add);
        String result = showAllTheTeamMembers.execute(team);

        Team team1 = taskManagementRepository.findTeamByName("Banditi");

        assertEquals(team1.getMembers().toString(), result);
    }



}
 