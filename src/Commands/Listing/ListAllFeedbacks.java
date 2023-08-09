package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;

import java.util.List;

public class ListAllFeedbacks implements Command {
    private List<Feedback> feedbackList;
    private List<Task> tasks;

    public ListAllFeedbacks(TaskManagementRepository taskManagementRepository){
        tasks = taskManagementRepository.getTasks();
    }


    @Override
    public String execute(List<String> parameters) {
        if(feedbackList.isEmpty()){
            return "There are no registered feedbacks.";
        }

        return ListingHelpers.elementsToString(getFeedbackFromTasks(tasks));
    }

    private List<Feedback> getFeedbackFromTasks(List<Task> tasks){
        for (Task task:tasks) {
            if(task instanceof Feedback){
                feedbackList.add((Feedback) task);
            }
        }
        return feedbackList;
    }
}
