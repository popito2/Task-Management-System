package Models.Tasks;

import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests {
    @Test
    public void constructor_Should_Throw_When_TitleIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new TaskImpl(1,"A", "This is a new task.", Status.ACTIVE));
    }
    @Test
    public void constructor_Should_Throw_When_DescriptionIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new TaskImpl(1,"NameOfTheTask", "A", Status.ACTIVE));
    }
    @Test
    public void constructor_Should_EXECUTE_When_TaskIsSetRight(){
        TaskImpl task = new TaskImpl(1,"NameOfTheTask", "This is a new task.", Status.ACTIVE);
        Assertions.assertEquals(Status.ACTIVE, task.getStatus());
    }
    @Test
    public void should_Create_Task_When_ValidValuesArePassed(){
        TaskImpl task = new TaskImpl(1,"NameOfTheTask", "This is a new task.", Status.ACTIVE);
        assertAll(
                () -> assertEquals(1,task.getId()),
                () -> assertEquals("NameOfTheTask",task.getTitle()),
                () -> assertEquals("This is a new task.",task.getDescription()),
                () -> assertEquals(Status.ACTIVE,task.getStatus())
        );
    }

}
