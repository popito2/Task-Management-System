package Models.Tasks;

import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Identifiable;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends Task implements Models.Tasks.Interfaces.Feedback {
    private int rating;

    public Feedback(int id, String title, String description, Status status, int rating) {
        super(id, title, description, status);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }



    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Rating: " + rating +
                "Title:" + getTitle();

    }

    public void advanceFeedback(){
        switch(getStatus()){
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


    public void revertFeedback(){
        switch(getStatus()){
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