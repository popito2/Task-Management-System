package Commands;

import Core.TaskManagementRepository;
import Models.interfaces.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowPersonsActivityTests {
    private TaskManagementRepository taskManagementRepository;
    private ShowPersonsActivity showPersonsActivity;

    @BeforeEach
    public void setUp(){
        taskManagementRepository = new TaskManagementRepository();
        showPersonsActivity = new ShowPersonsActivity(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = Arrays.asList(new String[2]);;

        assertThrows(IllegalArgumentException.class, () -> showPersonsActivity.execute(params));
    }

    @Test
    public void should_ShowPersonsActivity_When_ArgumentsAreValid(){
        CreateNewPerson createNewPerson = new CreateNewPerson(taskManagementRepository);
        List<String> person = List.of("James");
        createNewPerson.execute(person);

        Member member = taskManagementRepository.findMemberByName("James");

        List<String> params = List.of("James");
        String result = showPersonsActivity.execute(person);

        Assertions.assertEquals(member.getHistoryChanges().toString(),result);
    }
}
