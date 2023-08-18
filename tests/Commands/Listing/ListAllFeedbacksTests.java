package Commands.Listing;

import Core.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<String> parameters = new ArrayList<>();
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[Feedback{Title: Title 1, Rating: 4}, Feedback{Title: Title 2, Rating: 5}, Feedback{Title: Title 3, Rating: 3}, Feedback{Title: Title 4, Rating: 5}]", result);
    }

    @Test
    public void testExecute_FilterByTitle_ReturnsFilteredFeedbacks() {
        List<String> parameters = new ArrayList<>();
        parameters.add("filter");
        parameters.add("Title");
        parameters.add("Title 1");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("Feedback{Title: Title 1, Rating: 4}", result);
    }

    @Test
    public void testExecute_SortByTitle_ReturnsSortedFeedbacks() {
        List<String> parameters = new ArrayList<>();
        parameters.add("sort");
        parameters.add("Title");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[Feedback{Title: Title 1, Rating: 4}, Feedback{Title: Title 2, Rating: 5}, Feedback{Title: Title 3, Rating: 3}, Feedback{Title: Title 4, Rating: 5}]", result);
    }

    @Test
    public void testExecute_SortByRating_ReturnsSortedFeedbacks() {
        List<String> parameters = new ArrayList<>();
        parameters.add("sort");
        parameters.add("Rating");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[Feedback Title: Title 3, Rating: 3, Feedback Title: Title 1, Rating: 4, Feedback Title: Title 2, Rating: 5, Feedback Title: Title 4, Rating: 5]", result);
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
        List<String> parameters = new ArrayList<>();
        parameters.add("filter");
        parameters.add("Title");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[]", result);
    }

    @Test
    public void testExecute_InvalidCommand_ReturnsDefaultList() {
        List<String> parameters = new ArrayList<>();
        parameters.add("invalid");
        String result = listAllFeedbacks.execute(parameters);

        assertEquals("[Feedback{Title: Title 1, Rating: 4}, Feedback{Title: Title 2, Rating: 5}, Feedback{Title: Title 3, Rating: 3}, Feedback{Title: Title 4, Rating: 5}]", result);
    }
}
