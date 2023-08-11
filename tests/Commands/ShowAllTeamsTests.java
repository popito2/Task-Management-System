package Commands;

import Core.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTeamsTests {
    private TaskManagementRepository taskManagementRepository;
    private ShowAllTeams showAllTeams;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showAllTeams = new ShowAllTeams(taskManagementRepository);
    }



    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[4]);
        assertThrows(IllegalArgumentException.class, () -> showAllTeams.execute(params));
    }

    @Test
    public void should_ShowAllTeams_When_ArgumentsAreValid(){
        List<String> params = new ArrayList<>();
        Assertions.assertDoesNotThrow(() -> showAllTeams.execute(params));
    }
}
