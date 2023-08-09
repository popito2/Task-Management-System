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
    private List<Task> tasks = new ArrayList<>();
    private List<String> historyChanges = new ArrayList<>();

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


    @Override
    public void addTask(Models.Tasks.Task task) {
        this.tasks.add((Task) task);
    }

    @Override
    public void removeTask(Models.Tasks.Task task) {
        this.tasks.remove(task);

    }

    public void addHistory(String history){
        historyChanges.add(history);
    }
}
