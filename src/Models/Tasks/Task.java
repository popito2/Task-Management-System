package Models.Tasks;

import ActivityHistory.History;
import Models.Tasks.Enums.Status;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public static final int MIN_NAME_LENGTH = 10;
    public static final int MAX_NAME_LENGTH = 50;
    public static final String NAME_ERROR = "The name must be between 10 and 50 symbols.";
    public static final int DESCRIPTION_MIN = 10;
    public static final int DESCRIPTION_MAX = 500;
    public static final String DESCRIPTION_ERROR_MESSAGE = "Description must be between 10 and 500 symbols.";
    private int Id;
    private String title;
    private String description;
    private Status status;
    private List<String> comments;
    private List<String> historyChanges;

    public Task(int id, String title, String description, Status status ) {
        Id = id;
        setTitle(title);
        setDescription(description);
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    private void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateIntRange(title.length(), MIN_NAME_LENGTH,
                MAX_NAME_LENGTH, NAME_ERROR);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateIntRange(description.length(), DESCRIPTION_MIN,
                DESCRIPTION_MAX, DESCRIPTION_ERROR_MESSAGE);
        this.description= description;
    }

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }

    public List<String> getHistoryChanges() {
        return new ArrayList<>(historyChanges);
    }

    public void addHistoryChange(String historyChange){
        this.historyChanges.add(historyChange);
    }
}
