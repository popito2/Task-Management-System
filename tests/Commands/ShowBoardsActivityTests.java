package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Board;
import Models.interfaces.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowBoardsActivityTests {
    private TaskManagementRepository taskManagementRepository;
    private ShowBoardsActivity showBoardsActivity;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showBoardsActivity = new ShowBoardsActivity(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> showBoardsActivity.execute(params));
    }

}
