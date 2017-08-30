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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HTMSoftware extends Application {

    private final String[] COLOR_BY_POSITION = {"RED", "YELLOW", "BLUE", "GREEN"};
    protected static QuestionBank questionBank;
    protected static QuestionButton recentButton;
    protected static Label questionLabel, answerLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Gather competitors information
        String[] competitors = getInfo();
        QuestionInputBox questionsInput = new QuestionInputBox("Nhập dữ liệu câu hỏi");
        questionBank = new QuestionBank(questionsInput.getFile10(), questionsInput.getFile20(), questionsInput.getFile30());

        Stage window = primaryStage;
        window.setTitle("Chương trình Hành trình Magellan");

        // The main layout for the software
        BorderPane mainLayout = new BorderPane();

        mainLayout.setTop(makeTopLogo());
        mainLayout.setLeft(createCompetitorsInfo(competitors));

        createQuestionAndAnswerLabels();
        // Make the label to note the area of question and answer
        Label questionLogo = new Label("Câu hỏi");
        questionLogo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        Button answerButton = new Button(" Đáp án");
        answerButton.setFont(Font.font("Times New Roman", 20));

        answerButton.setOnAction(event -> {
            answerLabel.setText(questionBank.getCurrentAnswer());
        });

        //Make a VBox for the right area of BorderPane
        VBox questionArea = new VBox(20, questionLogo, questionLabel, answerButton, answerLabel);
        BorderPane.setMargin(questionArea, new Insets(0, 20, 0 ,0));
        mainLayout.setRight(questionArea);

        // Create all buttons and add them to the grid, a 6x6 square
        mainLayout.setCenter(makeGridButtons(6, 6));

        // Setting the main scene
        Scene mainScene = new Scene(mainLayout, 1300, 800);

        window.setScene(mainScene);
        window.show();
    }

    private GridQuestionButtons makeGridButtons(int numRows, int numCols) {
        // Create the grid of buttons
        GridQuestionButtons grid = new GridQuestionButtons(6, 6);
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

        colorButton.setOnAction(event -> {
            if (recentButton != null) {
                recentButton.color(color);
            }
        });

        lineOfInfo.getChildren().add(colorButton);

        return lineOfInfo;
    }

    private String[] getInfo() {
        InfoBox infoBox = new InfoBox();
        infoBox.display("Nhập dữ liệu thí sinh");
        return infoBox.getCompetitors();
    }

    private void createQuestionAndAnswerLabels() {
        // Create the label for question and answer
        // TODO: Long question will not fit always fit into the label area
        questionLabel = new Label("Hãy sẵn sàng!");
        questionLabel.setMaxWidth(500);
        questionLabel.setWrapText(true);
        questionLabel.setFont(Font.font("Times New Roman", 32));
        questionLabel.setTextAlignment(TextAlignment.JUSTIFY);

        answerLabel = new Label("Chúc các thí sinh may mắn");
        answerLabel.setMaxWidth(500);
        answerLabel.setWrapText(true);
        answerLabel.setFont(Font.font("Times New Roman", 32));
    }
}
