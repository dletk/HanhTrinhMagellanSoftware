import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
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

    }

    private void createButtons() {
        // Make a list to store all the buttons to shuffle later
        ArrayList<QuestionButton> buttons = new ArrayList<>();
        int questionValue = 10;
        int count = 0;
        // TODO: Make a random generator to assign value for button
        // TODO: Having a coordinate system a-f and 1-6 for the buttons grid

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Condition for count depends on the number of each question value in the game
                if (count == 14 || count == 26) {
                    questionValue += 10;
                }
                QuestionButton questionButton = new QuestionButton(questionValue);
//                GridPane.setConstraints(questionButton, col, row, 1, 1, HPos.CENTER, VPos.CENTER);
//                this.add(questionButton, col, row);
                buttons.add(questionButton);
//                this.getChildren().add(questionButton);
                count++;
            }
        }
        // Shuffle the buttons list and add all buttons to the GridPane
        Collections.shuffle(buttons);
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                this.add(buttons.get(index), col, row);
                index++;
            }
        }
    }

}
