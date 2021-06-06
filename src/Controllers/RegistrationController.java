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

public class RegistrationController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        RegistrationController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        RegistrationController.locale = locale;
    }

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Label passwordLabel1;

    @FXML
    private Label passwordLabel2;

    @FXML
    private Label loginLabel;

    @FXML
    private Button loginRegButton;

    @FXML
    private TextField loginTextField;


    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        String username = loginTextField.getText();
        String password = passwordField1.getText();
        String password2 = passwordField2.getText();
        if ((!username.equals("")) && (!password.equals(""))) {
            if (password.equals(password2)) {
                User user = new User(username, password);
                String message = Client.init(user, "newUser");
                if (message.equals("New user is successfully added")) {
                    Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
                    Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
                    MenuController.setStage(menuStage);
                } else {
                    CommandStatusController.showCommandStatus(message, "Error");
                }
            } else {
                CommandStatusController.showCommandStatus("Passwords don't match", "Error");
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
    void initialize() {
        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        loginLabel.setText(setString("loginReg.label"));
        passwordLabel1.setText(setString("password1.label"));
        passwordLabel2.setText(setString("password2.label"));
        loginRegButton.setText(setString("registration.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}
