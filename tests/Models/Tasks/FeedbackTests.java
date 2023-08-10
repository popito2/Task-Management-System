package Models.Tasks;

import Models.Tasks.Enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTests {
    @Test
    public void constructor_Should_EXECUTE_When_RatingIsCorrect(){
        Feedback feedback = new Feedback(1,"NameOfTheFeedback", "This is a new Feedback.", Status.NEW, 5);
        Assertions.assertEquals(5, feedback.getRating());
    }
    @Test
    public void constructor_Should_EXECUTE_When_StatusIsCorrect(){
        Feedback feedback = new Feedback(1,"NameOfTheFeedback", "This is a new Feedback.", Status.NEW, 5);
        Assertions.assertEquals(Status.NEW, feedback.getStatus());
    }
    @Test
    public void should_Create_Feedback_When_ValidValuesArePassed() {
        Feedback feedback = new Feedback(1, "NameOfTheFeedback", "This is a new Feedback.", Status.NEW, 5);
        assertAll(
                () -> assertEquals(1, feedback.getId()),
                () -> assertEquals("NameOfTheFeedback", feedback.getTitle()),
                () -> assertEquals("This is a new Feedback.", feedback.getDescription()),
                () -> assertEquals(Status.NEW, feedback.getStatus()),
                () -> assertEquals(5, feedback.getRating())
        );
    }
}
