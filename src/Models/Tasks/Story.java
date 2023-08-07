package Models.Tasks;

import Models.Tasks.Enums.Status;

import java.util.List;

public class Story extends Task{

    public Story(int id, String title, String description, Status status, List<String> comments, List<String> historyChanges) {
        super(id, title, description, status, comments, historyChanges);
    }
}
