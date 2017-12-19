import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HTMSoftware extends Application {

    // Constants for the setting and appearance of the program.
    private final String[] COLOR_BY_POSITION = {"RED", "ORANGE", "BLUE", "GREEN"};
    private final int WIDTH_OF_QUESTION = 400;
    private final int SCREEN_WIDTH = 1366;
    private final int SCREEN_HEIGHT = 800;
    // The questionBank model, managing questions and answer.
    protected static QuestionBank questionBank;
    // The most recent selected button, this is used to keep track on the button to color
    protected static QuestionButton recentButton;
    // These labels are passed to the button grid to display the question and answer for that question
    protected static Label questionLabel, answerLabel;

    private final int TIME_FOR_QUESTION = 15;
    // Indicating the amount of time left for the timer of each question
    private int amountOfTimeLeft = 15;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Gather competitors information
        String[] competitors = getInfo();
        // Get the input for questions
        QuestionInputBox questionsInput = new QuestionInputBox("Nhập dữ liệu câu hỏi");
        questionBank = new QuestionBank(questionsInput.getFile10(), questionsInput.getFile20(), questionsInput.getFile30());

        Stage window = primaryStage;
        window.setTitle("Chương trình Hành trình Magellan");
        window.getIcons().add(new Image(this.getClass().getResource("img/htmIcon.jpg").toString()));

        // The main layout for the software
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #6BF7FF");

        // Top area
        mainLayout.setTop(makeTopArea());
        // Left area
        mainLayout.setLeft(createLeftAreas(competitors));
        // Right area
        mainLayout.setRight(createRightArea());

        // Create all buttons and add them to the grid, a 6x6 square
        mainLayout.setCenter(makeGridButtons(6, 6));

        // Setting the main scene
        Scene mainScene = new Scene(mainLayout, SCREEN_WIDTH, SCREEN_HEIGHT);

        window.setScene(mainScene);
        window.show();
    }

    private GridQuestionButtons makeGridButtons(int numRows, int numCols) {
        // Create the grid of buttons
        GridQuestionButtons grid = new GridQuestionButtons(6, 6);
        BorderPane.setMargin(grid, new Insets(0, 20, 0, 20));

        return grid;
    }

    private HBox makeTopArea() {
        HBox topArea = new HBox(10);
        // The logo
        Label logo = new Label("Phần thi Chinh phục");
        logo.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 80));
        logo.setTextFill(Color.valueOf("#2478B1"));

        logo.setMinWidth(SCREEN_WIDTH - 170);
        logo.setAlignment(Pos.CENTER);
        HBox.setMargin(logo, new Insets(10, 10, 10, 50));

        // The timing button
        Button timingButton = createTimingButton();

        topArea.getChildren().addAll(logo, timingButton);
        BorderPane.setAlignment(topArea, Pos.CENTER);

        return topArea;
    }

    private VBox createLeftAreas(String[] competitors) {
        VBox leftArea = new VBox(50);
        // Style the table name so it appears clear and easy to read
        leftArea.setPadding(new Insets(0, 0, 0, 20));

        // Audio for color button when the answer is correct
        AudioClip colorSound = new AudioClip(this.getClass().getResource("sound/colorSound.mp3").toString());

        int pos = 0;
        for (String competitor: competitors) {
            HBox lineInfo = createIndivdualInfo(pos, competitor, colorSound);
            leftArea.getChildren().add(lineInfo);
            pos++;
        }
        leftArea.getChildren().add(createStarArea());

        return leftArea;
    }

    private HBox createIndivdualInfo(int position, String name, AudioClip colorSound) {
        String color = COLOR_BY_POSITION[position];
        HBox lineOfInfo = new HBox(20);

        // Name label
        Label nameLabel = new Label(name.toUpperCase());
        nameLabel.setFont(Font.font("Times New Roman", 30));
        nameLabel.setMinWidth(280);
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
                colorSound.play();
            }
        });

        lineOfInfo.getChildren().add(colorButton);

        return lineOfInfo;
    }

    private VBox createRightArea() {
        createQuestionAndAnswerLabels();
        // Make the label to note the area of question and answer
        Label questionLogo = new Label("Câu hỏi");
        questionLogo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        Button answerButton = new Button(" Đáp án");
        answerButton.setFont(Font.font("Times New Roman", 20));

        // Sound for displaying answer
        AudioClip answerSound = new AudioClip(this.getClass().getResource("sound/dapan.mp3").toString());
        answerButton.setOnAction(event -> {
            answerSound.play();
            answerLabel.setText(questionBank.getCurrentAnswer());
        });

        //Make a VBox for the right area of BorderPane
        VBox questionArea = new VBox(20, questionLogo, questionLabel, answerButton, answerLabel);
        BorderPane.setMargin(questionArea, new Insets(0, 20, 0 ,0));

        return questionArea;
    }

    /**
     * Create a Info window to collect competitors information
     * @return An array of String with names of competitors.
     */
    private String[] getInfo() {
        InfoBox infoBox = new InfoBox();
        infoBox.display("Nhập dữ liệu thí sinh");
        return infoBox.getCompetitors();
    }

    private void createQuestionAndAnswerLabels() {
        // Create the label for question and answer
        // TODO: Long question will not fit always fit into the label area
        int size_of_font = 28;
        questionLabel = new Label("Hãy sẵn sàng!");
        questionLabel.setMaxWidth(WIDTH_OF_QUESTION);
        questionLabel.setWrapText(true);
        questionLabel.setFont(Font.font("Times New Roman", size_of_font));
        questionLabel.setTextAlignment(TextAlignment.JUSTIFY);

        answerLabel = new Label("Chúc các thí sinh may mắn");
        answerLabel.setMaxWidth(WIDTH_OF_QUESTION);
        answerLabel.setWrapText(true);
        answerLabel.setFont(Font.font("Times New Roman", size_of_font));
    }

    private VBox createStarArea() {
        VBox starArea = new VBox(10);
        // REMEMBER: Using .getClass().getResource().toString() to make the resource to be packed with the program when export
        ImageView starImage = new ImageView(new Image(this.getClass().getResource("img/ngoisao.png").toString()));
        starArea.setAlignment(Pos.CENTER);
        // Set the width of the Star image and preserve the ratio
        starImage.setFitWidth(200);
        starImage.setPreserveRatio(true);
        // Disable the star at the beginning
        starImage.setVisible(false);

        // Create the sound to play when the star is displayed (selected)
        AudioClip starSound = new AudioClip(this.getClass().getResource("sound/ngoi_sao.mp3").toString());

        Button toggleStarButton = new Button("Ngôi sao hi vọng");
        toggleStarButton.setOnAction(event -> {
            starImage.setVisible(!starImage.isVisible());
            if (starImage.isVisible()) {
                starSound.play();
            }
        });
        starArea.getChildren().addAll(starImage, toggleStarButton);

        return starArea;
    }

    /**
     * This method creates a timing button for each question.
     * The text on the button indicates how much time is left for each question
     * After count down to 0, the text is reset and ready for new countdown
     *
     * @return the timing button to add to a layout
     */
    private Button createTimingButton() {
        Button timingButton = new Button(String.valueOf(amountOfTimeLeft));

        // Make the button bigger for audience to see the countdown
        timingButton.setMinWidth(90);
        timingButton.setMinHeight(90);
        timingButton.setFont(Font.font(60));
        timingButton.setPadding(new Insets(5));

        // The sound for time
        AudioClip timingSound = new AudioClip(this.getClass().getResource("sound/timingSound.mp3").toString());

        timingButton.setOnAction(event -> {
            // Create a new thread to handle the countdown without interfere the GUI thread
            // Using lambda. The right format is new Thread (new Runnable...)
            new Thread(() -> {
                timingSound.play();
                while (amountOfTimeLeft > 0) {
                    amountOfTimeLeft--;
                    // Because the current thread cannot work with the GUI, Playform.runLater will put the request from
                    // this thread to a queue for execution in the GUI thread.
                    Platform.runLater(() -> timingButton.setText(String.format("%02d", amountOfTimeLeft)));
                    try {
                        // Countdown 1 second
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                amountOfTimeLeft = TIME_FOR_QUESTION;
                while (timingSound.isPlaying()) {
                    // Wait for timing sound to stop
                }
                // Set the timing back to original value for next question
                Platform.runLater(() -> timingButton.setText(Integer.toString(amountOfTimeLeft)));
            }
            ).start();
        });

        return timingButton;
    }
}
