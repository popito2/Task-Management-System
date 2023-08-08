package Models.Tasks;

import Models.Member;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Bug extends Task implements Models.Tasks.Interfaces.Bug {

    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private String assignee;

    public Bug(int id, String title, String description, Status status, List<String> stepsToReproduce, Priority priority, Severity severity, String assignee) {
        super(id, title, description, status);
        this.assignee = assignee;
        this.priority = priority;
        this.stepsToReproduce = stepsToReproduce;
        this.severity = severity;
    }

    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    public Priority getPriority() {
        return priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getAssignee() {
        return assignee;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Status advanceBug() {
        if (getStatus() == Status.ACTIVE) {
            setStatus(Status.FIXED);
            return getStatus();
        } else {
            setStatus(Status.ACTIVE);
            return getStatus();
        }
    }
}
