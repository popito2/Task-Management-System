package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Team;
import Models.interfaces.Member;
import Utils.ValidationHelpers;

import java.util.List;

public class AddPersonToTeam implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 2;
    private TaskManagementRepository taskManagementRepository;

    private String teamName;
    private String memberName;

    public AddPersonToTeam(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if(parameters.size() != EXPECTED_NUMBER_OF_PARAMETERS){
            throw new IllegalArgumentException("Number of parameters should be 2");
        }

        Models.interfaces.Team team = getTeam(parameters);
        Member member = getMember(parameters);

        team.addMember(member);

        return String.format("%s has been added to team %s", memberName, teamName);
    }

    private Models.interfaces.Team getTeam(List<String> parameters){
        teamName = parameters.get(0);
        return new Team(teamName);
    }

    private Member getMember(List<String> parameters){
        memberName = parameters.get(1);
        return new Models.Member(memberName);
    }
}
