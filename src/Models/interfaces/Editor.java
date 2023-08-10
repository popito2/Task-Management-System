package Models.interfaces;

import Models.Tasks.Task;

public interface Editor {
     void addTask(Models.Tasks.Task task);
     void removeTask(Task task);
     void addHistory(String history);

}
