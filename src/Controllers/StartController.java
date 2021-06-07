package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class StartController {
    private static Stage stage;
    private static Locale locale = new Locale("en", "US");

    public static void setStage(Stage stage) {
        StartController.stage = stage;
    }

    ObservableList<String> languages = FXCollections.observableArrayList("English", "Russian", "Spanish (Honduras)", "Ukrainian", "Macedonian");

    @FXML
    private Button goToLoginButton;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private Label startLabel;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        setLocale();
        Parent content = FXMLLoader.load(getClass().getResource("../login.fxml"));
        Stage loginStage = ChangeWindowController.changeWindow(content, stage, "Login");
        LoginController.setStage(loginStage);
    }

    private void setLocale() {
        AddController.setLocale(locale);
        FilterContainsNameController.setLocale(locale);
        FilterLessThanAgeController.setLocale(locale);
        LoginController.setLocale(locale);
        MenuController.setLocale(locale);
        RegistrationController.setLocale(locale);
        RemoveByIdController.setLocale(locale);
        TableController.setLocale(locale);
        UpdateController.setLocale(locale);
        UpdateFromTableController.setLocale(locale);
        VisualisationController.setLocale(locale);
    }

    @FXML
    void initialize() {
        languageChoiceBox.setValue("English");
        languageChoiceBox.setItems(languages);

        languageChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String language = languageChoiceBox.getValue();
                if (language.equals("English")) locale = new Locale("en", "US");
                if (language.equals("Russian")) locale = new Locale("ru", "RU");
                if (language.equals("Spanish (Honduras)")) locale = new Locale("es", "HN");
                if (language.equals("Ukrainian")) locale = new Locale("uk", "UA");
                if (language.equals("Macedonian")) locale = new Locale("mk", "MK");

                ResourceBundle rb = ResourceBundle.getBundle("text", locale);

                String start = rb.getString("start.label");
                start = new String(start.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                startLabel.setText(start);
            }
        });
    }

}
