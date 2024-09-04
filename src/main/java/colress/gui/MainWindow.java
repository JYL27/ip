package colress.gui;

import colress.Colress;
import colress.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Colress colress;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image colressImage = new Image(this.getClass().getResourceAsStream("/images/Colress.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Colress instance */
    public void setColress(Colress c) {
        colress = c;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(colress.greetUser(), colressImage),
                DialogBox.getDukeDialog(colress.loadTasks(), colressImage),
                DialogBox.getDukeDialog(colress.getTasks(), colressImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Colress's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = colress.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, colressImage)
        );
        userInput.clear();
    }
}
