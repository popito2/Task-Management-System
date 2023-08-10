package Commands;

import Commands.Contracts.Command;
import Core.Contracts.TaskManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewTeamTests {
    private Command command;
    private TaskManagementRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new Core.TaskManagementRepository();
        this.command = new CreateNewTeam(repository);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(0);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
    //?
    @Test
    public void execute_Should_AddNewТеам_When_PassedValidInput() {
        List<String> params = List.of("NewTeam");
        command.execute(params);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, repository.getTeams().size()),
                () -> Assertions.assertEquals("NewTeam",repository.getTeams().get(0).getName()));
    }

    public class TestUtilities {

        /**
         * Returns a new List with size equal to wantedSize.
         * Useful when you do not care what the contents of the List are,
         * for example when testing if a list of a command throws exception
         * when it's parameters list's size is less/more than expected.
         *
         * @param wantedSize the size of the List to be returned.
         * @return a new List with size equal to wantedSize
         */
        public static List<String> getList(int wantedSize) {
            return Arrays.asList(new String[wantedSize]);
        }
    }
}
