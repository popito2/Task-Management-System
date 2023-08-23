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
        if (tasks.isEmpty()) {
            return "There are no tasks.";
        }

        if (parameters.size() == 0) {
            return ListingHelpers.elementsToString(tasks);
        } else if (parameters.size() == 1) {
            sortTasks(parameters);
        } else if (parameters.size() == 2) {
            filterTasks(parameters);
        }

        return ListingHelpers.elementsToString(tasks);
    }

    private String filterTasks(List<String> parameters) {
        if (parameters.get(0).equals("filter")) {
            List<Task> activeTasks = tasks.stream().filter(task -> task.getTitle().equals(parameters.get(1))).collect(Collectors.toList());
            return activeTasks.toString();
        }
        return "Wrong command";
    }

    private String sortTasks(List<String> parameters) {
        if (parameters.get(0).equals("sort")) {
            List<Task> sortedTasks = tasks.stream().sorted(Comparator.comparing(task -> task.getTitle())).collect(Collectors.toList());
            return sortedTasks.toString();
        }
        return "Wrong command";
    }
}
