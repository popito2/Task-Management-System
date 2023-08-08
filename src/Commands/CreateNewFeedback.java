package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateNewFeedback implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private TaskManagementRepository taskManagementRepository;

    private String title;
    private String description;
    private Status status;

    private int rating;


    public CreateNewFeedback(Core.Contracts.TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    public String execute(List<String> parameters){
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Feedback createdFeedback = taskManagementRepository.createNewFeedback(title, description, status, rating);

        return String.format("Task with ID %d was created.", createdFeedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        status = ParsingHelpers.tryParseEnum(parameters.get(2), Status.class);
        rating=Integer.parseInt(parameters.get(3));

    }
}


