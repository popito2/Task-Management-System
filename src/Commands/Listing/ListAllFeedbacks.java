package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllFeedbacks implements Command {
    private List<Feedback> feedbackList = new ArrayList<>();
    private List<Task> tasks;

    public ListAllFeedbacks(TaskManagementRepository taskManagementRepository){
        tasks = taskManagementRepository.getTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        if(feedbackList.isEmpty()){
            return "There are no registered feedback.";
        }

        List<Feedback> filteredFeedbacks;

        feedbackList = getFeedbackFromTasks(tasks);

        if (parameters.get(0).equals("filter")){

            if(parameters.get(1).equals("Title")){
                filteredFeedbacks = feedbackList.stream()
                        .filter(feedback -> feedback.getTitle().equals(parameters.get(2)))
                        .collect(Collectors.toList());
                return filteredFeedbacks.toString();
            }
        }
        if (parameters.get(0).equals("sort")){
            if(parameters.get(1).equals("Title")){
                List<Feedback> sortedFeedbacks = feedbackList.stream()
                        .sorted(Comparator.comparing(Feedback::getTitle))
                        .collect(Collectors.toList());
                return sortedFeedbacks.toString();
            }
            if(parameters.get(1).equals("Rating")){
                List<Feedback> sortedFeedbacks = feedbackList.stream()
                        .sorted(Comparator.comparing(Feedback::getRating))
                        .collect(Collectors.toList());
                return sortedFeedbacks.toString();
            }
        }


        return feedbackList.toString();
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
