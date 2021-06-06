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

public class FilterLessThanAgeController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        FilterLessThanAgeController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        FilterLessThanAgeController.locale = locale;
    }

    @FXML
    private Button goToMenuButton;

    @FXML
    private Label enterLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Button filterButton;

    @FXML
    private TextField ageTextField;

    @FXML
    void filter(ActionEvent event) {
        try {
            String ageCheck = ageTextField.getText();
            long age = Long.parseLong(ageCheck);
            CommandStatusController.showCommandStatus(Client.filterLessThanAge(age), "Filter less than age");
        } catch (NumberFormatException e) {
            CommandStatusController.showCommandStatus("Please enter a number", "Error");
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

        enterLabel.setText(setString("filterLessThanAge.label"));
        ageLabel.setText(setString("filterAge.label"));
        filterButton.setText(setString("filter.button"));
        goToMenuButton.setText(setString("goToMenu.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}

