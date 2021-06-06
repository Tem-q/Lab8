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

public class FilterContainsNameController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        FilterContainsNameController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        FilterContainsNameController.locale = locale;
    }

    @FXML
    private Button goToMenuButton;

    @FXML
    private Label enterLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button filterButton;

    @FXML
    private Label nameLabel;

    @FXML
    void filter(ActionEvent event) {
        String name = nameTextField.getText();
        if (!name.equals("")) {
            CommandStatusController.showCommandStatus(Client.filterContainsName(name), "Filter contains name");
        } else {
            CommandStatusController.showCommandStatus("Please enter the name", "Error");
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

        enterLabel.setText(setString("filterContainsName.label"));
        nameLabel.setText(setString("filterName.label"));
        filterButton.setText(setString("filter.button"));
        goToMenuButton.setText(setString("goToMenu.button"));

    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}
