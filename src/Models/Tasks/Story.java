package Models.Tasks;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;

public class Story extends TaskImpl implements Models.Tasks.Interfaces.Story {
    private Priority priority;
    private Size size;
    private String assignee;

    public Story(int id, String title, String description, Priority priority, Size size, Status status) {
        super(id, title, description, status);
        setPriority(priority);
        setSize(size);
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    public Priority getPriority() {
        return priority;
    }

    public Size getSize() {
        return size;
    }

    public String getAssignee() {
        return assignee;
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
