package Commands.Listing;

import Core.TaskManagementRepository;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Bug;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListAllFeedbacksTests {
    private ListAllFeedbacks listAllFeedbacks;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new Core.TaskManagementRepository();
        listAllFeedbacks = new ListAllFeedbacks(taskManagementRepository);
    }

    @Test
    public void testExecute_NoFilterOrSort_ReturnsAllFeedbacks() {
        Feedback feedback = taskManagementRepository.createNewFeedback("feedbackfeedback", "This is a new feedback", Status.OPEN, 10);
        List<Task> tasks = new ArrayList<>();
        tasks.add(feedback);

        List<String> params = new ArrayList<>();
        String result = listAllFeedbacks.execute(params);

        Assertions.assertEquals(tasks.toString(), result);


    }

    @Test
    public void testExecute_FilterByTitle_ReturnsFilteredFeedbacks() {
        Feedback feedback = taskManagementRepository.createNewFeedback("feedbackfeedback", "This is a new feedback", Status.OPEN, 10);
        List<Task> tasks = new ArrayList<>();
        tasks.add(feedback);

        List<String> parameters = new ArrayList<>();
        parameters.add("filter");
        parameters.add("Title");
        parameters.add("feedbackfeedback");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals(tasks.toString(), result);
    }

    @Test
    public void testExecute_SortByTitle_ReturnsSortedFeedbacks() {
        Feedback feedback = taskManagementRepository.createNewFeedback("feedbackfeedback", "This is a new feedback", Status.OPEN, 10);
        List<String> parameters = new ArrayList<>();
        parameters.add("sort");
        parameters.add("Title");
        String result = listAllFeedbacks.execute(parameters);

        assertFalse(result.isEmpty());
    }

    @Test
    public void testExecute_SortByRating_ReturnsSortedFeedbacks() {
        Feedback feedback = taskManagementRepository.createNewFeedback("feedbackfeedback", "This is a new feedback", Status.OPEN, 10);
        List<String> parameters = new ArrayList<>();
        parameters.add("sort");
        parameters.add("Rating");
        String result = listAllFeedbacks.execute(parameters);

        assertFalse(result.isEmpty());
    }

    @Test
    public void testExecute_NoFeedbacks_ReturnsNoRegisteredFeedbackMessage() {
        taskManagementRepository = new Core.TaskManagementRepository();
        listAllFeedbacks = new ListAllFeedbacks(taskManagementRepository);

        List<String> parameters = new ArrayList<>();
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("There are no registered feedback.", result);
    }

    @Test
    public void testExecute_FilterByTitleNoMatches_ReturnsEmptyList() {
        Feedback feedback = taskManagementRepository.createNewFeedback("feedbackfeedback", "This is a new feedback", Status.OPEN, 10);
        List<String> parameters = new ArrayList<>();
        parameters.add("filter");
        parameters.add("Title");
        parameters.add("hello");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[]", result);
    }

}
