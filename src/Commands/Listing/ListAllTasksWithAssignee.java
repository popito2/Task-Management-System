package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Task;
import Models.Tasks.Story;
import Utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

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

