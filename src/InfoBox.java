import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoBox {

    public void display(String title) {
        Stage window = new Stage(StageStyle.DECORATED);
        window.setTitle(title);

        GridPane layout = new GridPane();
        for (int position = 0; position < 4; position++) {
            layout.getChildren().add(createInfoLabel(position));
            layout.getChildren().add(createInfoTextField(position));
        }

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

    //Todo: Make a dropdown for each of competitors to set his/her color.
}
