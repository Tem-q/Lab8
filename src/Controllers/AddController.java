package Controllers;

import client.Client;
import dragon.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddController {
    private static Stage stage;
    private static String check;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage, String check) {
        AddController.stage = stage;
        AddController.check = check;
    }

    public static void setLocale(Locale locale) {
        AddController.locale = locale;
    }

    ObservableList<String> dragonTypes = FXCollections.observableArrayList("AIR","FIRE","UNDERGROUND");

    @FXML
    private Label killerLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label dragonLabel;

    @FXML
    private TextField personHeightTextField;

    @FXML
    private TextField locationZTextField;

    @FXML
    private Label personWeightLabel;

    @FXML
    private Label dragonWeightLabel;

    @FXML
    private TextField personWeightTextField;

    @FXML
    private Label personNameLabel;

    @FXML
    private TextField y_coordinateTextField;

    @FXML
    private TextField personNameTextField;

    @FXML
    private Label locationXLabel;

    @FXML
    private Label locationNameLabel;

    @FXML
    private Button goToMenuButton;

    @FXML
    private Label personHeightLabel;

    @FXML
    private TextField dragonDescriptionTextField;

    @FXML
    private Label y_coordinateLabel;

    @FXML
    private TextField dragonNameTextField;

    @FXML
    private Label dragonTypeLabel;

    @FXML
    private Label x_coordinateLabel;

    @FXML
    private Label dragonDescriptionLabel;

    @FXML
    private TextField locationNameTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextField locationYTextField;

    @FXML
    private Label dragonNameLabel;

    @FXML
    private TextField x_coordinateTextField;

    @FXML
    private TextField locationXTextField;

    @FXML
    private Label locationYLabel;

    @FXML
    private TextField dragonAgeTextField;

    @FXML
    private TextField dragonWeightTextField;

    @FXML
    private ChoiceBox<String> dragonTypeChoiceBox;

    @FXML
    private Label dragonAgeLabel;

    @FXML
    private Label locationZLabel;

    @FXML
    void add(ActionEvent event) {
        try {
            String dragonName = dragonNameTextField.getText();
            String dragonAgeCheck = dragonAgeTextField.getText();
            String dragonWeightCheck = dragonWeightTextField.getText();
            String dragonTypeCheck = dragonTypeChoiceBox.getValue();
            String x_coordinateCheck = x_coordinateTextField.getText();
            String y_coordinateCheck = y_coordinateTextField.getText();
            String description = dragonDescriptionTextField.getText();
            String locationName = locationNameTextField.getText();
            String xCheck = locationXTextField.getText();
            String yCheck = locationYTextField.getText();
            String zCheck = locationZTextField.getText();
            String personName = personNameTextField.getText();
            String personHeightCheck = personHeightTextField.getText();
            String personWeightCheck = personWeightTextField.getText();

            long x_coordinate = Long.parseLong(x_coordinateCheck);
            double y_coordinate = Double.parseDouble(y_coordinateCheck);
            float x = Float.parseFloat(xCheck);
            int y = Integer.parseInt(yCheck);
            float z = Float.parseFloat(zCheck);
            float personHeight = Float.parseFloat(personHeightCheck);
            long personWeight = Long.parseLong(personWeightCheck);
            long dragonAge = Long.parseLong(dragonAgeCheck);
            int dragonWeight = Integer.parseInt(dragonWeightCheck);
            DragonType dragonType = DragonType.valueOf(dragonTypeCheck);

            if (y_coordinate > 67) {
                CommandStatusController.showCommandStatus("The y coordinate must be less than or equal to 67. Try again","Error");
            } else if (personHeight <= 0) {
                CommandStatusController.showCommandStatus("The height of person must be greater than 0. Try again", "Error");
            } else if (personWeight <= 0) {
                CommandStatusController.showCommandStatus("The weight of person must be greater than 0. Try again", "Error");
            } else if (dragonAge <= 0) {
                CommandStatusController.showCommandStatus("The age of dragon must be greater than 0. Try again", "Error");
            } else if (dragonWeight <= 0) {
                CommandStatusController.showCommandStatus("The weight of dragon must be greater than 0. Try again", "Error");
            } else if ((dragonName.equals("")) || (locationName.equals("")) || (personName.equals(""))) {
                CommandStatusController.showCommandStatus("All fields must be filled in. Try again", "Error");
            } else {
                if (check.equals("add")) {
                    Dragon dragon = new Dragon(dragonName, new Coordinates(x_coordinate, y_coordinate), dragonAge, description, dragonWeight, dragonType, new Person(personName, personHeight, personWeight, new Location(x, y, z, locationName)));
                    CommandStatusController.showCommandStatus(Client.add(dragon), "Dragons");
                } else if (check.equals("addIfMax")) {
                    Dragon dragon = new Dragon(dragonName, new Coordinates(x_coordinate, y_coordinate), dragonAge, description, dragonWeight, dragonType, new Person(personName, personHeight, personWeight, new Location(x, y, z, locationName)));
                    CommandStatusController.showCommandStatus(Client.addIfMax(dragon), "Dragons");
                }
            }
        } catch (NumberFormatException e) {
            CommandStatusController.showCommandStatus("Check values and try again", "Error");
        }
    }

    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
        Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
        MenuController.setStage(menuStage);
    }

    @FXML
    void initialize(){
        dragonTypeChoiceBox.setValue("AIR");
        dragonTypeChoiceBox.setItems(dragonTypes);

        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        dragonLabel.setText(setString("dragon.label"));
        locationLabel.setText(setString("location.label"));
        killerLabel.setText(setString("killer.label"));
        dragonNameLabel.setText(setString("name.label"));
        dragonAgeLabel.setText(setString("dragonAge.label"));
        dragonWeightLabel.setText(setString("dragonWeight.label"));
        dragonTypeLabel.setText(setString("dragonType.label"));
        x_coordinateLabel.setText("x_coordinate.label");
        y_coordinateLabel.setText("y_coordinate.label");
        dragonDescriptionLabel.setText(setString("dragonDescription.label"));
        locationXLabel.setText(setString("locationX.label"));
        locationYLabel.setText(setString("locationY.label"));
        locationZLabel.setText(setString("locationZ.label"));
        locationNameLabel.setText(setString("name.label"));
        personHeightLabel.setText(setString("personHeight.label"));
        personWeightLabel.setText(setString("personWeight.label"));
        personNameLabel.setText(setString("name.label"));
        addButton.setText(setString("add.button"));
        goToMenuButton.setText(setString("goToMenu.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }
}
