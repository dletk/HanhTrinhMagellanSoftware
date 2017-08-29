import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class QuestionButton extends Button {

    private HashMap<Integer, String> notation = new HashMap<>();

    private final double WIDTH = 60;
    private final double HEIGHT = 60;

    private int questionValue;
    private boolean active;
    private Label questionLabel;
    private Label answerLabel;

    public QuestionButton(int questionValue) {
        super();

        notation.put(10, "");
        notation.put(20, "!");
        notation.put(30, "?");

        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);

        active = false;
        this.questionValue = questionValue;

        // Color the button based on the value of question
        this.setStyle("-fx-border-width: 0.5; -fx-border-color: black; -fx-font-size: 24px; -fx-text-fill: purple");
        this.setTextAlignment(TextAlignment.CENTER);

        notate();
        this.setOnAction(event -> color("RED"));
    }

    public boolean isActive() {
        return active;
    }

    public int getQuestionValue() {
        return questionValue;
    }

    public void notate() {
        this.setText(notation.get(questionValue));
    }

    public void color(String color) {
        this.setStyle("-fx-border-width: 0.5; -fx-border-color: black; -fx-background-color: " + color);
        this.setText("");
    }
}
