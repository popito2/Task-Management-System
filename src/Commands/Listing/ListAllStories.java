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

        List<Story> filteredStories;
        stories = getStoriesFromTasks(tasks);

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

        return stories.toString();
    }

    private List<Story> getStoriesFromTasks(List<Task> tasks){
        for (Task task:tasks) {
            if(task instanceof Feedback){
                stories.add((Story) task);
            }
        }
        return stories;
    }
}
