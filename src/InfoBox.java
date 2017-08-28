import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoBox {

    private String[] competitors;
    private Stage window;

    public void display(String title) {
        window = new Stage(StageStyle.DECORATED);
        window.setTitle(title);
        competitors = new String[4];

        GridPane layout = new GridPane();
        for (int position = 0; position < 4; position++) {
            layout.getChildren().add(createInfoLabel(position));
            layout.getChildren().add(createInfoTextField(position));
        }
        layout.getChildren().add(createSubmitButton(layout));


        // Make the layout stretch a bit to look more clear
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(5));

        Scene infoScene = new Scene(layout, 300, 200);
        window.setScene(infoScene);

        window.showAndWait();
    }

    private TextField createInfoTextField(int position) {
        // Using textfield to record the name of 4 competitors
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Nhập tên thí sinh");
        GridPane.setConstraints(nameTextField, 1, position);

        return nameTextField;
    }

    private Label createInfoLabel(int position) {
        // Make a label to introduce the input
        // The displayed position of competitor needs to be +1 for the purpose of common understanding for user
        Label nameLabel = new Label("Tên thí sinh " + Integer.toString(position + 1));
        GridPane.setConstraints(nameLabel, 0, position);

        return nameLabel;
    }

    private Button createSubmitButton(GridPane layout) {
        Button submit = new Button("Nhập");
        submit.setOnAction(event -> getCompetitorsName(layout));
        GridPane.setConstraints(submit, 1,4);
        return submit;
    }

    private void getCompetitorsName(GridPane layout) {
        int i = 0;
        for (Node child: layout.getChildren()) {
            if (child instanceof TextField) {
                competitors[i] = ((TextField) child).getText();
                i++;
            } else if (i == 4) {
                break;
            }
        }
        window.close();
    }

    public String[] getCompetitors() {
        return competitors;
    }
}
