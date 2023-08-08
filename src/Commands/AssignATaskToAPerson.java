package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Task;
import Models.Team;
import Models.interfaces.Member;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;

import java.util.List;

public class AssignATaskToAPerson implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 2;
    private TaskManagementRepository taskManagementRepository;

    private int taskId;
    private String memberName;

    public AssignATaskToAPerson(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);

        Task task = getTask(parameters);

        Member member = getMember(parameters);

        member.addTask(task);

        return String.format("%s has been added to team %s", memberName, taskId);
    }

        private Task getTask(List<String> parameters){
        taskId = ParsingHelpers.tryParseInt(parameters.get(0),"id");
            Task task = taskManagementRepository.findTaskById(taskId);
            return task;
    }

    private Member getMember(List<String> parameters){
        memberName = parameters.get(1);
        return new Models.Member(memberName);
    }
}



