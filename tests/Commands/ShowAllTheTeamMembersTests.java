package Commands;

import Core.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTheTeamMembersTests {

    private TaskManagementRepository taskManagementRepository;
    private ShowAllTheTeamMembers showAllTheTeamMembers;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showAllTheTeamMembers = new ShowAllTheTeamMembers(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> showAllTheTeamMembers.execute(params));
    }



}
 