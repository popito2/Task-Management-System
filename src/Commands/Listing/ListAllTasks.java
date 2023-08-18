package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        if (parameters.get(0).equals("filter")) {
            List<Task> activeTasks = tasks.stream().filter(task -> task.getTitle().equals(parameters.get(1))).collect(Collectors.toList());
            return activeTasks.toString();
        }
        if(parameters.get(0).equals("sort")){
            List<Task> sortedTasks = tasks.stream().sorted(Comparator.comparing(task -> task.getTitle())).collect(Collectors.toList());
            return sortedTasks.toString();
        }

        return ListingHelpers.elementsToString(tasks);
    }
}
