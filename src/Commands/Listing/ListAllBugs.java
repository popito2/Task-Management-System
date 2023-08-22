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
    private TaskManagementRepository taskManagementRepository;

    public ListAllBugs(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        List<Bug> filteredBugs;
        List<Bug> bugs;

        bugs = getBugsFromTasks(taskManagementRepository.getTasks());

        if(bugs.isEmpty()){
            return "There are no registered bugs.";
        }

        if (parameters.size()==0){
            return bugs.toString();
        }
        else if(parameters.size()==2){
            if (parameters.get(0).equals("sort")){
                if(parameters.get(1).equals("Title")){
                    List<Bug> sortedBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getTitle))
                            .collect(Collectors.toList());
                    return sortedBugs.toString();
                }
                if(parameters.get(1).equals("Severity")){
                    List<Bug> sortedBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getSeverity))
                            .collect(Collectors.toList());
                    return sortedBugs.toString();
                }
                if(parameters.get(1).equals("Priority")){
                    List<Bug> sortedBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getPriority))
                            .collect(Collectors.toList());
                    return sortedBugs.toString();
                }
            }
        }
        else if(parameters.size()==3){
            if (parameters.get(0).equals("filter")){
                if(parameters.get(1).equals("Assignee")){
                    filteredBugs = bugs.stream()
                            .filter(bug -> bug.getAssignee().equals(parameters.get(2)))
                            .collect(Collectors.toList());
                    return filteredBugs.toString();
                }
                if(parameters.get(1).equals("Status")){
                    filteredBugs = bugs.stream()
                            .filter(bug -> bug.getStatus().equals(ParsingHelpers.tryParseEnum(parameters.get(2), Status.class)))
                            .collect(Collectors.toList());
                    return filteredBugs.toString();
                }
            }
        }

        return bugs.toString();
    }

    private List<Bug> getBugsFromTasks(List<Task> tasks){
        List<Bug> bugs = new ArrayList<>();
        for (Task task:tasks) {
            if(task instanceof Bug){
                bugs.add((Bug) task);
            }
        }
        return bugs;
    }


}
