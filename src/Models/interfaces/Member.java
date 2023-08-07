package Models.interfaces;

import Models.Tasks.Interfaces.Task;

import java.util.List;

public interface Member {
    String getName();
    List<Task> getTasks();
    List<String> getHistoryChanges();
}
