package Models.Tasks;

import Models.Tasks.Enums.Status;

public class Feedback extends TaskImpl implements Models.Tasks.Interfaces.Feedback {
    private int rating;

    public Feedback(int id, String title, String description, Status status, int rating) {
        super(id, title, description, status);
        this.rating = rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void advanceFeedback() {
        switch (getStatus()) {
            case NEW:
                setStatus(Status.UNSCHEDULED);
                break;
            case UNSCHEDULED:
                setStatus(Status.SCHEDULED);
                break;
            case SCHEDULED:
                setStatus(Status.DONE);
                break;
        }
    }


    public void revertFeedback() {
        switch (getStatus()) {
            case DONE:
                setStatus(Status.SCHEDULED);
                break;
            case SCHEDULED:
                setStatus(Status.UNSCHEDULED);
                break;
            case UNSCHEDULED:
                setStatus(Status.NEW);
                break;
        }
    }
}