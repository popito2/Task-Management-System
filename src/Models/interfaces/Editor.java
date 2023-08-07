package Models.interfaces;

public interface Editor {
     void addTask(Models.Tasks.Task task);
     void removeTask(Models.Tasks.Task task);
     void addHistory(String history);

}
