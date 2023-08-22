import Core.Contracts.TaskManagementRepository;
import Core.TaskManagementEngine;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TaskManagementEngine taskManagementEngine = new TaskManagementEngine();
        taskManagementEngine.start();
    }

}
