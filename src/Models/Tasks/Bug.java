package Models.Tasks;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Bug extends TaskImpl implements Models.Tasks.Interfaces.Bug {

    private List<String> stepsToReproduce = new ArrayList<>();
    private Priority priority;
    private Severity severity;
    private String assignee;

    public Bug(int id, String title, String description, Status status, Priority priority, Severity severity) {
        super(id, title, description, status);
        this.priority = priority;
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

    public void setAssignee(String assignee){
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

    public void addListOfSteps(String step){
        stepsToReproduce.add(step);
    }
}
