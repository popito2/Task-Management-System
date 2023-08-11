package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Member;
import Models.interfaces.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowTeamsActivityTests {

    private TaskManagementRepository taskManagementRepository;
    private ShowTeamsActivity showTeamsActivity;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepository();
        showTeamsActivity = new ShowTeamsActivity(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = Arrays.asList(new String[2]);


        assertThrows(IllegalArgumentException.class, () -> showTeamsActivity.execute(params));
    }

    @Test
    public void should_ShowPersonsActivity_When_ArgumentsAreValid() {
        CreateNewTeam createNewTeam = new CreateNewTeam(taskManagementRepository);
        List<String> team = List.of("James");
        createNewTeam.execute(team);

        Team team1 = taskManagementRepository.findTeamByName("James");

        List<String> params = List.of("James");
        String result = showTeamsActivity.execute(team);

        Assertions.assertEquals(team1.getHistory().toString(), result);
    }
}


