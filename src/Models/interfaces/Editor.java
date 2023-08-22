package Models.interfaces;

import Models.Tasks.TaskImpl;

public interface Editor {
     void addTask(TaskImpl task);
     void removeTask(TaskImpl task);
     void addHistory(String history);

}
