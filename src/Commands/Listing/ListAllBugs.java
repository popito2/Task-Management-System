package Commands.Listing;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import Utils.ListingHelpers;
import Utils.ParsingHelpers;

import java.util.ArrayList;
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

        bugs = getBugsFromTasks(tasks);

        if (ParsingHelpers.tryParseEnum(parameters.get(0), Status.class) == Status.ACTIVE) {
            List<Bug> activeBugs = bugs.stream()
                    .filter(bug -> bug.getStatus() == Status.ACTIVE)
                    .collect(Collectors.toList());
            return activeBugs.toString();
        }

        if (ParsingHelpers.tryParseEnum(parameters.get(0), Status.class) == Status.FIXED) {
            List<Bug> fixedBugs = bugs.stream()
                    .filter(bug -> bug.getStatus() == Status.FIXED)
                    .collect(Collectors.toList());
            return fixedBugs.toString();
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
