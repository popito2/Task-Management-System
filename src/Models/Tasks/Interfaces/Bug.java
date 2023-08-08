package Models.Tasks.Interfaces;

import Models.Member;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;

import java.util.List;

public interface Bug extends Task{
    List<String> getStepsToReproduce();
    Priority getPriority();
    Severity getSeverity();
    Member getAssignee();
    Status advanceBug();
}
