package Controllers;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveByIdController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        RemoveByIdController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        RemoveByIdController.locale = locale;
    }

    @FXML
    private TextField idTextField;

    @FXML
    private Button goToMenuButton;

    @FXML
    private Label idLabel;

    @FXML
    private Label enterLabel;

    @FXML
    private Button removeByIdButton;

    @FXML
    void removeById(ActionEvent event) throws IOException {
        try {
            String idCheck = idTextField.getText();
            int id = Integer.parseInt(idCheck);
            CommandStatusController.showCommandStatus(Client.removeById(id), "Error");
        } catch (NumberFormatException e) {
            CommandStatusController.showCommandStatus("Please enter the id of dragon. It must be int type", "Error");
        }
    }

    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
        Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
        MenuController.setStage(menuStage);
    }

    @FXML
    void initialize() {
        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        enterLabel.setText(setString("remove.label"));
        idLabel.setText(setString("removeId.label"));
        removeByIdButton.setText(setString("remove.button"));
        goToMenuButton.setText(setString("goToMenu.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}
