package Models.Tasks.Interfaces;

import Models.Tasks.Enums.Status;

import java.util.List;

public interface Task extends Identifiable {
    String getTitle();
    String getDescription();
    Status getStatus();
    List<String> getComments();
    List<String> getHistoryChanges();
}
