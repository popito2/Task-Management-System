package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Member;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeople implements Command {

    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 0;
    private final List<Member> members;

    public ShowAllPeople(TaskManagementRepository taskManagementRepository){
        members = taskManagementRepository.getMembers();
    }



    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        List<String> memberNames = new ArrayList<>();
        if(members.isEmpty()){
            return "There are no listed members";
        }
        for (Member member:members) {
            memberNames.add(member.getName());
        }

        return memberNames.toString();
    }
}
