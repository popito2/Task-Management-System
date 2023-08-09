package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Story;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllStories implements Command {
    private List<Story> stories = new ArrayList<>();
    private List<Task> tasks;

    public ListAllStories(TaskManagementRepository taskManagementRepository){
        tasks = taskManagementRepository.getTasks();
    }


    @Override
    public String execute(List<String> parameters) {
        if(stories.isEmpty()){
            return "There are no registered stories.";
        }

        return ListingHelpers.elementsToString(getFeedbackFromTasks(tasks));
    }

    private List<Story> getFeedbackFromTasks(List<Task> tasks){
        for (Task task:tasks) {
            if(task instanceof Feedback){
                stories.add((Story) task);
            }
        }
        return stories;
    }
}
