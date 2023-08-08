package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.interfaces.Member;
import Utils.ValidationHelpers;

import java.util.List;

public class CreateNewPerson implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    private TaskManagementRepository taskManagementRepository;

    private String name;

    public CreateNewPerson(Core.Contracts.TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);

        name = parameters.get(0);

        Member member = taskManagementRepository.createNewPerson(name);

        return String.format("New member %s has been created", member.getName());
    }
}
