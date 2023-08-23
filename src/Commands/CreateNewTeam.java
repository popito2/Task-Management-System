package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Story;
import Models.interfaces.Team;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.List;

public class CreateNewTeam implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    private String name;
    private TaskManagementRepository taskManagementRepository;

    public CreateNewTeam(Core.Contracts.TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        Team createdNewTeam = taskManagementRepository.createNewTeam(name);
        return String.format("Team with name: %s was created.", createdNewTeam.getName());
    }

    private void parseParameters(List<String> parameters) {
        this.name = parameters.get(0);
    }
}
