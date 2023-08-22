package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Story;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllStories implements Command {
    private TaskManagementRepository taskManagementRepository;

    public ListAllStories(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        List<Story> filteredStories;
        List<Story> stories;
        stories = getStoriesFromTasks(taskManagementRepository.getTasks());

        if(stories.isEmpty()){
            return "There are no registered stories.";
        }

        if(parameters.size()==0){
            return stories.toString();
        }else if(parameters.size()==2){
            if (parameters.get(0).equals("sort")){
                if(parameters.get(1).equals("Title")){
                    List<Story> sortedStories = stories.stream()
                            .sorted(Comparator.comparing(Story::getTitle))
                            .collect(Collectors.toList());
                    return sortedStories.toString();
                }
                if(parameters.get(1).equals("Priority")){
                    List<Story> sortedStories = stories.stream()
                            .sorted(Comparator.comparing(Story::getPriority))
                            .collect(Collectors.toList());
                    return sortedStories.toString();
                }
                if(parameters.get(1).equals("Size")){
                    List<Story> sortedStories = stories.stream()
                            .sorted(Comparator.comparing(Story::getSize))
                            .collect(Collectors.toList());
                    return sortedStories.toString();
                }
            }
        }else if(parameters.size()==3){
            if (parameters.get(0).equals("filter")){
                if(parameters.get(1).equals("Status")){
                    filteredStories = stories.stream()
                            .filter(story -> story.getStatus().equals(ParsingHelpers.tryParseEnum(parameters.get(2), Status.class)))
                            .collect(Collectors.toList());
                    return filteredStories.toString();
                }
                if(parameters.get(1).equals("Assignee")){
                    filteredStories = stories.stream()
                            .filter(story -> story.getAssignee().equals(parameters.get(2)))
                            .collect(Collectors.toList());
                    return filteredStories.toString();
                }
            }
        }

        return stories.toString();
    }

    private List<Story> getStoriesFromTasks(List<Task> tasks){
        List<Story> stories = new ArrayList<>();
        for (Task task:tasks) {
            if(task instanceof Story){
                stories.add((Story) task);
            }
        }
        return stories;
    }
}
