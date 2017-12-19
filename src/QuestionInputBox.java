import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * The class creating window to get the input for questions
 */
public class QuestionInputBox {

    private Stage window;
    private Button button10, button20, button30;
    private File file10, file20, file30;
    private Label path10, path20, path30;

    public QuestionInputBox(String title) {
        window = new Stage(StageStyle.DECORATED);
        window.setTitle(title);

        GridPane mainLayout = new GridPane();
        mainLayout.setVgap(20);
        mainLayout.setHgap(30);
        mainLayout.setPadding(new Insets(30));

        button10 = createSelectFileButton(mainLayout, "10", 1, 1);
        button20 = createSelectFileButton(mainLayout, "20", 1, 2);
        button30 = createSelectFileButton(mainLayout, "30", 1, 3);

        Button finishButton = new Button("Hoàn tất");
        mainLayout.add(finishButton, 3, 4);
        finishButton.setOnAction(event -> {
            window.close();
        });

        path10 = new Label();
        path10.setMaxWidth(300);
        mainLayout.add(path10, 2, 1);
        path20 = new Label();
        path20.setMaxWidth(300);
        mainLayout.add(path20, 2, 2);
        path30 = new Label();
        path30.setMaxWidth(300);
        mainLayout.add(path30, 2, 3);


        Scene fileSelectScene = new Scene(mainLayout, 800, 300);
        window.setScene(fileSelectScene);
        window.showAndWait();
    }

    private Button createSelectFileButton(GridPane layout, String questionValue, int col, int row) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn dữ liệu cho gói câu hỏi " + questionValue + " điểm");
        Button fileSelect = new Button("Chọn File dữ liệu");
        Label prompt = new Label("Gói câu hỏi " + questionValue + " điểm");


        layout.add(prompt, col, row);
        layout.add(fileSelect, col + 2, row);

        fileSelect.setOnAction(event -> getQuestionFile(event, fileChooser));

        return fileSelect;
    }

    private void getQuestionFile(ActionEvent event, FileChooser fileChooser) {
        File questionFile = fileChooser.showOpenDialog(window);
        try {
            if (event.getSource().equals(button10)) {
                file10 = questionFile;
                displayPath(file10.getCanonicalPath(), "10");
            } else if (event.getSource().equals(button20)) {
                file20 = questionFile;
                displayPath(file20.getCanonicalPath(), "20");
            } else if (event.getSource().equals(button30)) {
                file30 = questionFile;
                displayPath(file30.getCanonicalPath(), "30");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPath(String path, String type) {
        switch (type) {
            case "10":
                path10.setText(path);
                break;
            case "20":
                path20.setText(path);
                break;
            default:
                path30.setText(path);
                break;
        }
    }

    public File getFile10() {
        return file10;
    }

    public File getFile20() {
        return file20;
    }

    public File getFile30() {
        return file30;
    }
}
