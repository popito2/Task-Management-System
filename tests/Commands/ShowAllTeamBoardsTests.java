package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Board;
import Models.interfaces.Member;
import Models.interfaces.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowAllTeamBoardsTests {
    private TaskManagementRepository taskManagementRepository;
    private ShowAllTeamBoards showAllTeamBoards;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showAllTeamBoards = new ShowAllTeamBoards(taskManagementRepository);
    }
    @Test
    public void should_ShowAllTeamBoards_When_ArgumentsAreValid(){
        CreateNewTeam createNewTeam = new CreateNewTeam(taskManagementRepository);
        CreateANewBoardInATeam createANewBoardInATeam = new CreateANewBoardInATeam(taskManagementRepository);

        List<String> team = List.of("Banditi");
        List<String> board = List.of("Banditi","James");

        createNewTeam.execute(team);
        createANewBoardInATeam.execute(board);
        String result = showAllTeamBoards.execute(team);

        Team team1 = taskManagementRepository.findTeamByName("Banditi");

        assertEquals(team1.getBoards().toString(), result);
    }
}