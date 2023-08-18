package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Task;
import Models.Tasks.Story;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasksWithAssignee implements Command {
    private final TaskManagementRepository taskManagementRepository;


    public ListAllTasksWithAssignee(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        List<Task> tasks = taskManagementRepository.getTasks();
        List<Task>tasksWithAssignee = filterTasks(tasks);

        if (tasksWithAssignee.isEmpty()) {
            return "No tasks found.";
        }


        if (parameters.get(0).equals("filter")) {
            if (parameters.get(1).equals("status")) {
                List<Task> filteredByStatus = tasks.stream()
                        .filter(task -> task.getStatus() == ParsingHelpers.tryParseEnum(parameters.get(2), Status.class))
                        .collect(Collectors.toList());
                return filteredByStatus.toString();
            }
            /*if (parameters.get(1).equals("assignee")) {
                List<Task> filteredByAssignee = tasks.stream()
                        .filter(task -> task.getStatus() == ParsingHelpers.tryParseEnum(parameters.get(2), ListAllTasksWithAssignee.class))
                        .collect(Collectors.toList());
                return filteredByAssignee.toString();
            }*/
        }
        if(parameters.get(0).equals("sort")){
            List<Task> sortedTasks = tasks.stream().sorted(Comparator.comparing(task -> task.getTitle())).collect(Collectors.toList());
            return sortedTasks.toString();
        }

        return ListingHelpers.elementsToString(tasksWithAssignee);
    }

    public List<Task>filterTasks(List<Task>tasks){
        List<Task>tasksWithAssignee = new ArrayList<>();
        for (Task task:tasks) {
            if(task instanceof Bug || task instanceof Story){
                tasksWithAssignee.add(task);
            }

        }
        return tasksWithAssignee;
    }
}

