import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HTMSoftware extends Application {

    private final String[] COLOR_BY_POSITION = {"RED", "YELLOW", "BLUE", "GREEN"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Gather competitors information
        String[] competitors = getInfo();
        QuestionInputBox getQuestion = new QuestionInputBox("Nhập dữ liệu câu hỏi");

        Stage window = primaryStage;
        window.setTitle("Chương trình Hành trình Magellan");

        // The main layout for the software
        BorderPane mainLayout = new BorderPane();

        mainLayout.setTop(makeTopLogo());
        mainLayout.setLeft(createCompetitorsInfo(competitors));

        // Create the label for question and answer
        Label questionLabel = new Label("Câu hỏi số 1, đây là 1 câu hỏi.....");
        Label answerLabel = new Label("Đáp án: Something");
        //Make a VBox for the right area of BorderPane
        VBox questionArea = new VBox(20, questionLabel, answerLabel);
        mainLayout.setRight(questionArea);

        // Create all buttons and add them to the grid, a 6x6 square
        mainLayout.setCenter(makeGridButtons(6, 6, questionLabel, answerLabel));

        // Setting the main scene
        Scene mainScene = new Scene(mainLayout, 1200, 600);

        window.setScene(mainScene);
        window.show();
    }

    private GridQuestionButtons makeGridButtons(int numRows, int numCols, Label questionLabel, Label answerLabel) {
        // Create the grid of buttons
        GridQuestionButtons grid = new GridQuestionButtons(6, 6, questionLabel, answerLabel);
        BorderPane.setMargin(grid, new Insets(0, 20, 0, 20));

        return grid;
    }

    private Label makeTopLogo() {
        Label logo = new Label("Phần thi Chinh phục");
        logo.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 100));
        logo.setTextFill(Color.valueOf("BLUE"));

        // The position of logo
        BorderPane.setAlignment(logo, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(10));

        return logo;
    }

    private VBox createCompetitorsInfo(String[] competitors) {
        VBox leftNamesTable = new VBox(50);
        int pos = 0;
        for (String competitor: competitors) {
            HBox lineInfo = createIndivdualInfo(pos, competitor);
            leftNamesTable.getChildren().add(lineInfo);
            pos++;
        }

        // Style the table name so it appears clear and easy to read
        leftNamesTable.setMinWidth(200);
        leftNamesTable.setPadding(new Insets(0, 0, 0, 20));

        return leftNamesTable;
    }

    private HBox createIndivdualInfo(int position, String name) {
        String color = COLOR_BY_POSITION[position];
        HBox lineOfInfo = new HBox(20);

        // Name label
        Label nameLabel = new Label(name.toUpperCase());
        nameLabel.setFont(Font.font("Times New Roman", 30));
        nameLabel.setMinWidth(300);
        nameLabel.setTextFill(Color.web(COLOR_BY_POSITION[position]));
        lineOfInfo.getChildren().add(nameLabel);

        // Color button
        Button colorButton = new Button();
        colorButton.setStyle("-fx-background-color: "+color);
        colorButton.setPrefWidth(30);
        colorButton.setPrefHeight(30);
        lineOfInfo.getChildren().add(colorButton);

        return lineOfInfo;
    }

    private String[] getInfo() {
        InfoBox infoBox = new InfoBox();
        infoBox.display("Nhập dữ liệu thí sinh");
        return infoBox.getCompetitors();
    }
}
