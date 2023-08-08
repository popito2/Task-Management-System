package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Team;
import Utils.ValidationHelpers;

import java.lang.reflect.Parameter;
import java.util.List;

public class ShowAllTheTeamMembers implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    private String name;
    private TaskManagementRepository taskManagementRepository ;

    public ShowAllTheTeamMembers(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        Team team = getTeam(parameters);
        return team.getMembers().toString();
    }
    private Team getTeam(List<String>parameters){
        name=parameters.get(0);
        return taskManagementRepository.findTeamByName(name);
    }
}
