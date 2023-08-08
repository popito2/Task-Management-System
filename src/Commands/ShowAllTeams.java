package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Team;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeams implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 0;
    private List<Team> teams;
    public ShowAllTeams(TaskManagementRepository taskManagementRepository) {
        teams = taskManagementRepository.getTeams();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        List<String> teamsName = new ArrayList<>();
        if (teams.isEmpty()){
            return "There are no teams listed";
        }
        for (Team teams: teams) {
            teamsName.add(teams.getName());
        }
        return teamsName.toString();
    }
}
