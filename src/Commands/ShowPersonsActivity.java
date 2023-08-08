package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Utils.ValidationHelpers;

import java.util.List;

public class ShowPersonsActivity implements Command {

    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    private List<String> history;
    private String name;
    private TaskManagementRepository taskManagementRepository;

    public ShowPersonsActivity(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        history = getHistory(parameters);
        return history.toString();
    }

    private List<String> getHistory(List<String> parameters){
        name = parameters.get(0);
        return taskManagementRepository.findMemberByName(name).getHistoryChanges();
    }
}
