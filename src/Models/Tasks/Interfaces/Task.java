package Models.Tasks.Interfaces;

import java.util.List;

public interface Task extends Identifiable {
    String getTitle();
    String getDescription();
    List<String> getComments();
    List<String> getHistoryChanges();
}
