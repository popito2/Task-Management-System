package Models;

import Models.Tasks.Interfaces.Task;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class Member implements Models.interfaces.Member {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_ERROR_MESSAGE = "Name should be between 5 and 15 symbols.";
    private String name;
    private List<Task> tasks;
    private List<String> historyChanges;

    public Member(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_ERROR_MESSAGE);
        this.name = name;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<String> getHistoryChanges() {
        return new ArrayList<>(historyChanges);
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void removeTask(Task task){
        this.tasks.remove(task);
    }

    public void addHistoryChange(String historyChange){
        historyChanges.add(historyChange);
    }
}
