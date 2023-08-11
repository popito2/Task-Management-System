package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Team;
import Utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoards implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 0;
    private TaskManagementRepository taskManagementRepository;

    public ShowAllTeamBoards(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        String name = parameters.get(0);
        Team team = taskManagementRepository.findTeamByName(name);
        return team.getBoards().toString();
    }

}
