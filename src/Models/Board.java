package Models;

import Models.Tasks.Interfaces.Task;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class Board implements Models.interfaces.Board {
    public static final int MIN_NAME_LENGTH = 5;
    public static final int MAX_NAME_LENGTH = 10;
    public static final String NAME_ERROR_MESSAGE = "Name must be between 5 adn 10 symbols!";
    private String name;
    private List<Task> tasks;
    private List<String> history;

    public Board(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.history = new ArrayList<>();
    }


    public void addHistoryEntry(String entry) {
        history.add(entry);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MIN_NAME_LENGTH, MAX_NAME_LENGTH, NAME_ERROR_MESSAGE);
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Board: " + name;
    }

    @Override
    public void addTask(Models.Tasks.Task task) {
        this.tasks.add((Task) task);

    }

    @Override
    public void removeTask(Models.Tasks.Task task) {
        this.tasks.remove(task);

    }

    @Override
    public void addHistory(String history) {
        this.history.add(history);


    }
}
