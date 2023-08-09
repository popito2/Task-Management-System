package Commands;


import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Story;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.List;

public class CreateNewStory implements Command {
    private String title;
    private String description;
    private Priority priority;
    private Size size;
    private Status status;
    private String assignee;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 5;
    private TaskManagementRepository taskManagementRepository;
    public CreateNewStory(Core.Contracts.TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        Story createdStory = taskManagementRepository.createNewStory(title, description, priority, size, status);
        return String.format("Task with ID %d was created.", createdStory.getId());
    }
    private void parseParameters(List<String> parameters){
        title = parameters.get(1);
        description = parameters.get(0);
        priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        size = ParsingHelpers.tryParseEnum(parameters.get(3), Size.class);
        status = ParsingHelpers.tryParseEnum(parameters.get(4), Status.class);
    }
}
