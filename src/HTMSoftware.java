import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HTMSoftware extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Gather competitors information
        getInfo();

        window = primaryStage;
        window.setTitle("Chương trình Huyền thoại Magellan");

        // The main layout for the software
        BorderPane mainLayout = new BorderPane();

        mainLayout.setTop(makeTopLogo());


        // Create all buttons and add them to the grid, a 6x6 square
        mainLayout.setCenter(makeGridButtons(6, 6));

        // Setting the main scene
        Scene mainScene = new Scene(mainLayout, 800, 600);

        window.setScene(mainScene);
        window.show();
    }

    private GridPane makeGridButtons(int numRows, int numCols) {
        // The grid to contains the all the question button
        GridPane gridButtons = new GridPane();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button questionButton = new Button();
                questionButton.setStyle("-fx-background-color: palegreen; -fx-border-width: 0.5; -fx-border-color: black");
                GridPane.setConstraints(questionButton, col, row, 1, 1, HPos.CENTER, VPos.CENTER);
                gridButtons.getChildren().add(questionButton);
            }
        }

        return gridButtons;
    }

    private Label makeTopLogo() {
        Label logo = new Label("Phần thi về đích");
        logo.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 45));
        logo.setTextFill(Color.valueOf("BLUE"));

        // The position of logo
        BorderPane.setAlignment(logo, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(10));

        return logo;
    }

//    private VBox createCompetitorInfo() {
        //TODO: Make the left column be the name of 4 competitors, with names are input from user (enter at the beginning)

//    }

    private void getInfo() {
        InfoBox infoBox = new InfoBox();
        infoBox.display("Nhập dữ liệu thí sinh");
    }
}
