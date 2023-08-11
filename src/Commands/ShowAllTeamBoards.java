package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Team;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamBoards implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 0;
    private TaskManagementRepository taskManagementRepository;
    private String name;

    public ShowAllTeamBoards(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = new Core.TaskManagementRepository();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        return getTeam(parameters).getBoards().toString();
    }
    private Team getTeam(List<String>parameters){
        name=parameters.get(0);
        return taskManagementRepository.findTeamByName(name);
    }
}
