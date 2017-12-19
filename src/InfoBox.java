import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The class generating a window to collect competitors' info
 */
public class InfoBox {

    // Store name of competitors
    private String[] competitors;
    // The current window of InfoBox
    private Stage window;

    /**
     * Method to begin display the Info box
     * @param title The title for the window of Info box
     */
    public void display(String title) {
        window = new Stage(StageStyle.DECORATED);
        window.setTitle(title);
        competitors = new String[4];

        // Using a GridPane as the layout for the Info Box
        GridPane layout = new GridPane();
        // position: The standing position of competitors
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

    /**
     * Creating a text field next to the label of instruction for input to record the name of competitor
     * @param position the standing position of the competitor
     * @return A textField for user to enter corresponding competitor's name
     */
    private TextField createInfoTextField(int position) {
        // Using textfield to record the name of 4 competitors
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Nhập tên thí sinh");
        GridPane.setConstraints(nameTextField, 1, position);

        return nameTextField;
    }

    /**
     * Make a label to introduce the name of required input
     * @param position the standing position of the competitor
     * @return a label telling which competitor's name should be entered
     */
    private Label createInfoLabel(int position) {
        // The displayed position of competitor needs to be +1 for the purpose of common understanding for user
        Label nameLabel = new Label("Tên thí sinh " + Integer.toString(position + 1));
        GridPane.setConstraints(nameLabel, 0, position);

        return nameLabel;
    }

    /**
     * Click on this button to record the entered information
     * @param layout the layout in with the button lied on.
     * @return the submit button
     */
    private Button createSubmitButton(GridPane layout) {
        Button submit = new Button("Nhập");
        // The submit button will trigger recording the competitors name when clicked
        submit.setOnAction(event -> {
            getCompetitorsName(layout);
            window.close();
        });
        GridPane.setConstraints(submit, 1,4);
        return submit;
    }

    /**
     * Extract the user's name from the text fields
     * @param layout the layout containing text fields to be extracted info
     */
    private void getCompetitorsName(GridPane layout) {
        int i = 0;
        for (Node child: layout.getChildren()) {
            if (child instanceof TextField) {
                // child is an Object now, need to be casted
                competitors[i] = ((TextField) child).getText();
                i++;
            } else if (i == 4) {
                // After getting 4 names, break the loop asap
                break;
            }
        }
    }

    /**
     * Getter for competitors name
     * @return
     */
    public String[] getCompetitors() {
        return competitors;
    }
}
