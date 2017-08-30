import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;

public class GridQuestionButtons extends GridPane {

    private int numCols;
    private int numRows;

    public GridQuestionButtons(int col, int row) {
        super();
        numCols = col;
        numRows = row;

        this.setHgap(1);
        this.setVgap(1);

        createButtons();
        createCoordinateSystem();

    }

    private void createButtons() {
        // Make a list to store all the buttons to shuffle later
        ArrayList<QuestionButton> buttons = new ArrayList<>();
        int questionValue = 10;
        int count = 0;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Condition for count depends on the number of each question value in the game
                if (count == 14 || count == 26) {
                    questionValue += 10;
                }
                QuestionButton questionButton = new QuestionButton(questionValue);
                buttons.add(questionButton);
                count++;
            }
        }
        // Shuffle the buttons list and add all buttons to the GridPane
        Collections.shuffle(buttons);
        int index = 0;
        // Position start at 1 to leave the 0 col and row for the coordinate system label
        for (int row = 1; row <= numRows; row++) {
            for (int col = 1; col <= numCols; col++) {
                this.add(buttons.get(index), col, row);
                index++;
            }
        }
    }

    private void createCoordinateSystem() {
        String[] colLabel = {"A", "B", "C", "D", "E", "F"};
        for (int index = 1; index <= numCols; index++) {
            Label letter = new Label(colLabel[index-1]);
            letter.setMinWidth(60);
            letter.setAlignment(Pos.CENTER);
            GridPane.setMargin(letter, new Insets(0, 0, 5, 0));
            this.add(letter, index, 0);

            Label num = new Label(Integer.toString(index));
            num.setMinHeight(60);
            GridPane.setMargin(num, new Insets(0, 5, 0, 0));
            this.add(num, 0, index);
        }
    }
}
