package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Story;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Bug> filteredBugs;

        bugs = getBugsFromTasks(tasks);

        if (parameters.get(0).equals("filter")){
            if(parameters.get(1).equals("Priority")){
                filteredBugs = bugs.stream()
                        .filter(bug -> bug.getPriority() == ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class))
                        .collect(Collectors.toList());
                return filteredBugs.toString();
            }
            if(parameters.get(1).equals("Severity")){
                filteredBugs = bugs.stream()
                        .filter(bug -> bug.getSeverity() == ParsingHelpers.tryParseEnum(parameters.get(2), Severity.class))
                        .collect(Collectors.toList());
                return filteredBugs.toString();
            }
            if(parameters.get(1).equals("Title")){
                filteredBugs = bugs.stream()
                        .filter(bug -> bug.getTitle() == parameters.get(2))
                        .collect(Collectors.toList());
                return filteredBugs.toString();
            }
        }
        if (parameters.get(0).equals("sort")){
            if(parameters.get(1).equals("Title")){
                List<Bug> sortedBugs = bugs.stream()
                        .sorted(Comparator.comparing(Bug::getTitle))
                        .collect(Collectors.toList());
                return sortedBugs.toString();
            }
            if(parameters.get(1).equals("Assignee")){
                List<Bug> sortedBugs = bugs.stream()
                        .sorted(Comparator.comparing(Bug::getAssignee))
                        .collect(Collectors.toList());
                return sortedBugs.toString();
            }
        }


        return bugs.toString();
    }

    private List<Bug> getBugsFromTasks(List<Task> tasks){
        for (Task task:tasks) {
            if(task instanceof Feedback){
                bugs.add((Bug) task);
            }
        }
        return bugs;
    }
}
