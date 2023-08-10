package Models.Tasks;

import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryTests {

    @Test
    public void constructor_Should_EXECUTE_When_PriorityIsCorrect(){
        Story story = new Story(1,"NameOfTheStory", "This is a new Story.", Priority.HIGH, Size.LARGE, Status.DONE);
        Assertions.assertEquals(Priority.HIGH,story.getPriority());
    }
    @Test
    public void constructor_Should_EXECUTE_When_SizeIsCorrect(){
        Story story = new Story(1,"NameOfTheStory", "This is a new Story.", Priority.HIGH, Size.LARGE, Status.DONE);
        Assertions.assertEquals(Size.LARGE,story.getSize());
    }
    @Test
    public void should_Create_Story_When_ValidValuesArePassed() {
        Story story = new Story(1,"NameOfTheStory", "This is a new Story.", Priority.HIGH, Size.LARGE, Status.DONE);
        assertAll(
                () -> assertEquals(1, story.getId()),
                () -> assertEquals("NameOfTheStory", story.getTitle()),
                () -> assertEquals("This is a new Story.", story.getDescription()),
                () -> assertEquals(Priority.HIGH,story.getPriority()),
                () -> assertEquals(Size.LARGE,story.getSize()),
                () -> assertEquals(Status.DONE,story.getStatus())
        );
    }
    @Test
    public void should_Advance_StoryStatus_WhenCalledAdvancedStoryStatus(){
        Story story = new Story(1,"NameOfTheStory", "This is a new Story.", Priority.HIGH, Size.LARGE, Status.IN_PROGRESS);
        story.advanceStoryStatus();
        Assertions.assertEquals(Status.DONE,story.getStatus());
    }
    @Test
    public void should_Reverse_StoryStatus_WhenCalledReverseStoryStatus(){
        Story story = new Story(1,"NameOfTheStory", "This is a new Story.", Priority.HIGH, Size.LARGE, Status.IN_PROGRESS);
        story.reverseStoryStatus();
        Assertions.assertEquals(Status.NOT_DONE,story.getStatus());
    }
}
