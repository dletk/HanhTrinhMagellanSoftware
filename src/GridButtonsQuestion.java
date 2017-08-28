import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GridButtonsQuestion extends GridPane {

    private int numCols;
    private int numRows;

    public GridButtonsQuestion(int col, int row) {
        super();
        numCols = col;
        numRows = row;

        this.setHgap(1);
        this.setVgap(1);

        createButtons();

    }

    private void createButtons() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button questionButton = new Button();

                questionButton.setStyle("-fx-background-color: palegreen; -fx-border-width: 0.5; -fx-border-color: black");
                questionButton.setMinWidth(60);
                questionButton.setMinHeight(60);

                GridPane.setConstraints(questionButton, col, row, 1, 1, HPos.CENTER, VPos.CENTER);
                this.getChildren().add(questionButton);
            }
        }
    }

}
