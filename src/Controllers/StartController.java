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
    //private ResourceBundle rb;

    public static void setStage(Stage stage) {
        StartController.stage = stage;
    }

    /*public static void setLocale(Locale locale) {
        StartController.locale = locale;
    }*/

    ObservableList<String> languages = FXCollections.observableArrayList("English", "Russian", "Spanish (Honduras)", "Ukrainian", "Macedonian");

    @FXML
    private Button goToLoginButton;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private Label enterLabel;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        String language = languageChoiceBox.getValue();
        if (language.equals("English")) locale = new Locale("en", "US");
        if (language.equals("Russian")) locale = new Locale("ru", "RU");
        if (language.equals("Spanish (Honduras)")) locale = new Locale("es", "HN");
        if (language.equals("Ukrainian")) locale = new Locale("uk", "UA");
        if (language.equals("Macedonian")) locale = new Locale("mk", "MK");
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

        /*languageChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String language = languageChoiceBox.getValue();
                if (language.equals("English")) locale = new Locale("en", "US");
                if (language.equals("Russian")) locale = new Locale("ru", "RU");
                if (language.equals("Spanish (Honduras)")) locale = new Locale("es", "HN");
                if (language.equals("Ukrainian")) locale = new Locale("uk", "UA");
                if (language.equals("Macedonian")) locale = new Locale("mk", "MK");

                ResourceBundle rb = ResourceBundle.getBundle("text", locale);

                /*String buttonText = rb.getString("entrance.button");
                buttonText = new String(buttonText.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                String nick = rb.getString("nickname.label");
                nick = new String(nick.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                String password = rb.getString("password.label");
                password = new String(password.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                String langs = rb.getString("languages.label");
                langs = new String(langs.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                String header = rb.getString("login.label");
                header = new String(header.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                entrance.setText(buttonText);
                nickLabel.setText(nick);
                passwordLabel.setText(password);
                languagesLabel.setText(langs);
                headerLabel.setText(header);
            }
        });*/
    }

}
