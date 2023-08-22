package Models.interfaces;

import Models.Tasks.Interfaces.Task;
import Models.Tasks.TaskImpl;

import java.util.List;

public interface Member extends Editor{
    String getName();
    List<TaskImpl> getTasks();
    List<String> getHistoryChanges();
}
