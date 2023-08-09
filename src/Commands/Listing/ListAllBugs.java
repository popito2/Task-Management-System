package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllBugs implements Command {
    private List<Bug> bugs = new ArrayList<>();
    private List<Task> tasks;

    public ListAllBugs(TaskManagementRepository taskManagementRepository){
        tasks = taskManagementRepository.getTasks();
    }


    @Override
    public String execute(List<String> parameters) {
        if(bugs.isEmpty()){
            return "There are no registered bugs.";
        }

        return ListingHelpers.elementsToString(getFeedbackFromTasks(tasks));
    }

    private List<Bug> getFeedbackFromTasks(List<Task> tasks){
        for (Task task:tasks) {
            if(task instanceof Feedback){
                bugs.add((Bug) task);
            }
        }
        return bugs;
    }
}
