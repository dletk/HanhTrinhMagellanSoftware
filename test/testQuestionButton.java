import javafx.application.Application;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class testQuestionButton {

    @Ignore
    public void testConstructor() {
        QuestionButton aButton = new QuestionButton(10);
        Assert.assertNotNull(aButton);
    }
}
