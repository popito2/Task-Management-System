package Models.Tasks;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;

import java.util.List;

public class Story extends Task implements Models.Tasks.Interfaces.Story {
    private Priority priority;
    private Size size;
    private String assignee;
    public Story(int id, String title, String description, Priority priority, Size size, Status status, String assignee) {
        super(id, title, description, status);
        setAssignee(assignee);
        setPriority(priority);
        setSize(size);
    }

    public Priority getPriority() {
        return priority;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Size getSize() {
        return size;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    public String getAssignee() {
        return assignee;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void advanceStoryStatus() {
        switch (getStatus()) {
            case NOT_DONE:
                setStatus(Status.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                setStatus(Status.DONE);
                break;
        }
    }

    public void reverseStoryStatus() {
        switch (getStatus()) {
            case DONE:
                setStatus(Status.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                setStatus(Status.NOT_DONE);
                break;
        }
    }

}
