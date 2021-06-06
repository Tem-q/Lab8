package Controllers;

import client.Client;
import dragon.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateFromTableController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        UpdateFromTableController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        UpdateFromTableController.locale = locale;
    }

    ObservableList<String> dragonTypes = FXCollections.observableArrayList("AIR","FIRE","UNDERGROUND");

    private static String idInit = null;
    private static String nameInit = null;
    private static String x_coordinateInit = null;
    private static String y_coordinateInit = null;
    private static String ageInit = null;
    private static String descriptionInit = null;
    private static String weightInit = null;
    private static String typeInit = null;
    private static String killer_nameInit = null;
    private static String killer_heightInit = null;
    private static String killer_weightInit = null;
    private static String xInit = null;
    private static String yInit = null;
    private static String zInit = null;
    private static String location_nameInit = null;

    public static void setAllInit(String id, String name, String x_coordinate, String y_coordinate, String age,
                                  String description, String weight, String type, String killer_name, String killer_height,
                                  String killer_weight, String x, String y, String z, String location_name){
        idInit = id;
        nameInit = name;
        x_coordinateInit = x_coordinate;
        y_coordinateInit = y_coordinate;
        ageInit = age;
        descriptionInit = description;
        weightInit = weight;
        typeInit = type;
        killer_nameInit = killer_name;
        killer_heightInit = killer_height;
        killer_weightInit = killer_weight;
        xInit = x;
        yInit = y;
        zInit = z;
        location_nameInit = location_name;
    }

    @FXML
    private TextField personHeightTextField;

    @FXML
    private Label y_coordinateLabel;

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
    private Button cancelButton;

    @FXML
    private TextField personNameTextField;

    @FXML
    private Label dragonLabel;

    @FXML
    private Label locationXLabel;

    @FXML
    private Button updateButton;

    @FXML
    private TextField y_coordinateTextField;

    @FXML
    private Label locationNameLabel;

    @FXML
    private Label personHeightLabel;

    @FXML
    private TextField dragonDescriptionTextField;

    @FXML
    private TextField dragonNameTextField;

    @FXML
    private Label dragonTypeLabel;

    @FXML
    private Label killerLabel;

    @FXML
    private Label dragonDescriptionLabel;

    @FXML
    private TextField locationNameTextField;

    @FXML
    private Label locationLabel;

    @FXML
    private TextField locationYTextField;

    @FXML
    private Label dragonNameLabel;

    @FXML
    private TextField locationXTextField;

    @FXML
    private Label locationYLabel;

    @FXML
    private TextField dragonAgeTextField;

    @FXML
    private TextField x_coordinateTextField;

    @FXML
    private TextField dragonWeightTextField;

    @FXML
    private ChoiceBox<String> dragonTypeChoiceBox;

    @FXML
    private Label dragonAgeLabel;

    @FXML
    private Label locationZLabel;

    @FXML
    private Label x_coordinateLabel;


    @FXML
    void update(ActionEvent event) {
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

            int id = Integer.parseInt(idInit);
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
                Dragon dragon = new Dragon(id,dragonName, new Coordinates(x_coordinate, y_coordinate), dragonAge, description, dragonWeight, dragonType, new Person(personName, personHeight, personWeight, new Location(x, y, z, locationName)));
                String answer = Client.update(dragon);
                if (answer.equals("Dragon successfully updated")) {
                    CommandStatusController.showCommandStatus(answer, "Dragons");
                } else {
                    CommandStatusController.showCommandStatus("It's not your dragon", "Error");
                }
            }
        } catch (NumberFormatException e) {
            CommandStatusController.showCommandStatus("Check values and try again", "Error");
        }
    }

    @FXML
    void close(ActionEvent event) {
        stage.close();
    }

    @FXML
    void initialize(){
        dragonTypeChoiceBox.setItems(dragonTypes);

        dragonNameTextField.setText(nameInit);
        dragonAgeTextField.setText(ageInit);
        dragonWeightTextField.setText(weightInit);
        dragonDescriptionTextField.setText(descriptionInit);
        dragonTypeChoiceBox.setValue(typeInit);
        x_coordinateTextField.setText(x_coordinateInit);
        y_coordinateTextField.setText(y_coordinateInit);

        locationXTextField.setText(xInit);
        locationYTextField.setText(yInit);
        locationZTextField.setText(zInit);
        locationNameTextField.setText(location_nameInit);

        personNameTextField.setText(killer_nameInit);
        personHeightTextField.setText(killer_heightInit);
        personWeightTextField.setText(killer_weightInit);

        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        dragonLabel.setText(setString("dragonUpdate.label"));
        locationLabel.setText(setString("locationUpdate.label"));
        killerLabel.setText(setString("killerUpdate.label"));
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
        updateButton.setText(setString("update.button"));
        cancelButton.setText(setString("cancel.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }
}
