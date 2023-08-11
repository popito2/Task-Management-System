package Commands.Listing;

import Commands.AddStepsToABug;
import Commands.CreateNewPerson;
import Core.Contracts.TaskManagementRepository;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListAllFeedbacksTests {
    private TaskManagementRepository taskManagementRepository;

    private List<Task> tasks;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new Core.TaskManagementRepository();
        tasks = new ArrayList<>(taskManagementRepository.getTasks());
    }

    @Test
    void testNoFeedbacks() {
        ListAllFeedbacks listAllFeedbacks = new ListAllFeedbacks(taskManagementRepository);
        tasks.removeIf(task -> task instanceof Feedback);
        String result = listAllFeedbacks.execute(new ArrayList<>());
        assertEquals("There are no registered feedbacks.", result);
    }

}

