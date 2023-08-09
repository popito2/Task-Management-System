package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementEngine;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Bug;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class AddStepsToABug implements Command {
    TaskManagementRepository taskManagementRepository;
    int id;

    public AddStepsToABug(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return getSteps(parameters).getStepsToReproduce().toString();
    }

    private Bug getSteps(List<String> parameters){
        id = ParsingHelpers.tryParseInt(parameters.get(0), "id");
        Bug bug = (Bug) taskManagementRepository.findTaskById(id);

        for(int i = 1; i<parameters.size(); i++){
            bug.addListOfSteps(parameters.get(i));
        }
        return bug;
    }
}
