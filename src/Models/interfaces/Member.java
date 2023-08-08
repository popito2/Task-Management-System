package Models.interfaces;

import Models.Tasks.Interfaces.Task;

import java.util.List;

public interface Member extends Editor{
    String getName();
    List<Task> getTasks();
    List<String> getHistoryChanges();
    void addTask(Task task);
}
