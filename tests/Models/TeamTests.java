package Models;

import Models.Tasks.Enums.Status;
import Models.Tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTests {
    @Test
    public void constructor_Should_Throw_When_NameIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new Team("A"));
    }
    @Test
    public void should_Create_Task_When_ValidValuesArePassed(){
        Team team = new Team("TeamName");
        assertAll(() -> assertEquals("TeamName",team.getName()));
    }
}
