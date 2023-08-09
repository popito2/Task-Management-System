package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllTasks implements Command {
    private List<Task> tasks;

    public ListAllTasks(TaskManagementRepository taskManagementRepository) {
        this.tasks = taskManagementRepository.getTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        if(tasks.isEmpty()) {
            return "There are no tasks.";
        }
        return ListingHelpers.elementsToString(tasks);
    }
}
