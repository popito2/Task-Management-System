package Models.Tasks.Interfaces;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;

public interface Story extends Task{
    Priority getPriority();
    Size getSize();
    String getAssignee();
}
