package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Member;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateNewBug implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private TaskManagementRepository taskManagementRepository;

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Severity severity;
    private String assignee;

    public CreateNewBug(Core.Contracts.TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    public String execute(List<String> parameters){
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Bug createdBug = taskManagementRepository.createNewBug(title, description, status, priority, severity);

        return String.format("Task with ID %d was created.", createdBug.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(1);
        description = parameters.get(0);
        status = ParsingHelpers.tryParseEnum(parameters.get(2), Status.class);
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
    }
}
