package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Feedback;
import Models.Team;
import Models.interfaces.Board;
import Models.interfaces.Member;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.List;

public class CreateANewBoardInATeam implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 2;
    private TaskManagementRepository taskManagementRepository;

    private String teamName;
    private String boardName;

    public CreateANewBoardInATeam(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);

        Models.interfaces.Team team = getTeam(parameters);
        Board board = getBoard(parameters);

        team.addBoard(board);

        return String.format("%s has been added to team %s", boardName, teamName);
    }

    private Models.interfaces.Team getTeam(List<String> parameters) {
        teamName = parameters.get(0);
        return taskManagementRepository.findTeamByName(teamName);
    }

    private Board getBoard(List<String> parameters) {
        boardName = parameters.get(1);
        return new Models.Board(boardName);
    }
}

