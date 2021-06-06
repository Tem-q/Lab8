package Controllers;

import client.Client;
import data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        LoginController.locale = locale;
    }

    @FXML
    private Label enterLabel;

    @FXML
    private Label registrationLabel;

    @FXML
    private Button registrationButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        String username = loginTextField.getText();
        String password = passwordField.getText();
        if ((!username.equals("")) && (!password.equals(""))) {
            User user = new User(username, password);
            String message = Client.init(user, "loginUser");
            if (message.equals("User signed in successfully")) {
                Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
                Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
                MenuController.setStage(menuStage);
            } else {
                CommandStatusController.showCommandStatus(message, "Error");
            }
        } if ((username.equals("")) && (!password.equals(""))) {
            CommandStatusController.showCommandStatus("You didn't enter a username", "Error");
        } if ((!username.equals("")) && (password.equals(""))) {
            CommandStatusController.showCommandStatus("You didn't enter a password", "Error");
        } if ((username.equals("")) && (password.equals(""))) {
            CommandStatusController.showCommandStatus("You didn't enter a username and a password", "Error");
        }
    }

    @FXML
    void goToRegistration(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../registration.fxml"));
        Stage registrationStage = ChangeWindowController.changeWindow(content, stage, "Registration");
        RegistrationController.setStage(registrationStage);
    }

    @FXML
    void initialize() {
        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        enterLabel.setText(setString("enter.button"));
        loginLabel.setText(setString("login.label"));
        passwordLabel.setText(setString("password.label"));
        registrationLabel.setText("registration.label");
        loginButton.setText(setString("login.button"));
        registrationButton.setText(setString("registration.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}
