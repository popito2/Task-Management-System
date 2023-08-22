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
    private List<Task> tasks;
    List<Task> tasksWithAssignee = filterTasks(tasks);

    public ListAllTasksWithAssignee(TaskManagementRepository taskManagementRepository) {
        tasks = taskManagementRepository.getTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        if (tasksWithAssignee.isEmpty()) {
            return "No tasks found.";
        }
        if(parameters.size()==0){
            return ListingHelpers.elementsToString(tasksWithAssignee);
        }else if(parameters.size()==1){
            return sortTasksWithAssignee(parameters);
        }else if(parameters.size()==3){
            return filterTasksWithAssignee(parameters);
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

    private String filterTasksWithAssignee(List<String> parameters){
        if (parameters.get(0).equals("filter")) {
            if (parameters.get(1).equals("status")) {
                List<Task> filteredByStatus = tasks.stream()
                        .filter(task -> task.getStatus() == ParsingHelpers.tryParseEnum(parameters.get(2), Status.class))
                        .collect(Collectors.toList());
                return filteredByStatus.toString();
            }
        }
        return "Wrong command";
    }

    private String sortTasksWithAssignee(List<String> parameters){
        if(parameters.get(0).equals("sort")){
            List<Task> sortedTasks = tasks.stream().sorted(Comparator.comparing(task -> task.getTitle())).collect(Collectors.toList());
            return sortedTasks.toString();
        }
        return "Wrong command";
    }
}

