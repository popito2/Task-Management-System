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
    private TaskManagementRepository taskManagementRepository;
    private List<Feedback> filteredFeedbacks;
    private List<Feedback> feedbackList;

    public ListAllFeedbacks(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        feedbackList = getFeedbackFromTasks(taskManagementRepository.getTasks());

        if(feedbackList.isEmpty()){
            return "There are no registered feedback.";
        }

        if(parameters.size()==0){
            return feedbackList.toString();
        }else if(parameters.size()==2){
            return sortFeedback(parameters);
        }else if(parameters.size()==3){
            return filterFeedback(parameters);
        }

        return feedbackList.toString();
    }

    private List<Feedback> getFeedbackFromTasks(List<Task> tasks){
        List<Feedback> feedbackList = new ArrayList<>();
        for (Task task:tasks) {
            if(task instanceof Feedback){
                feedbackList.add((Feedback) task);
            }
        }
        return feedbackList;
    }

    private String filterFeedback(List<String> parameters){
        if (parameters.get(0).equals("filter")){
            if(parameters.get(1).equals("Title")){
                filteredFeedbacks = feedbackList.stream()
                        .filter(feedback -> feedback.getTitle().equals(parameters.get(2)))
                        .collect(Collectors.toList());
                return filteredFeedbacks.toString();
            }
        }
        return "Wrong command";
    }

    private String sortFeedback(List<String> parameters){
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
        return "Wrong command";
    }
}
