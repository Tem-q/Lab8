package Controllers;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        MenuController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        MenuController.locale = locale;
    }

    @FXML
    private Label menuLabel;

    @FXML
    private Button tableButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button removeHeadButton;

    @FXML
    private Button filterContainsNameButton;

    @FXML
    private Button addButton;

    @FXML
    private Button headButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button visualisationButton;

    @FXML
    private Button filterLessThanAgeButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button addMaxButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button sumOfAgeButton;

    @FXML
    void help(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.help(), "Help");
    }

    @FXML
    void info(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.info(), "Info");
    }

    @FXML
    void goToAdd(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../add.fxml"));
        Stage addStage = ChangeWindowController.changeWindow(content, stage, "Add dragon");
        AddController.setStage(addStage, "add");
    }

    @FXML
    void goToAddIfMax(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../add.fxml"));
        Stage addStage = ChangeWindowController.changeWindow(content, stage, "Add dragon if weight is max");
        AddController.setStage(addStage, "addIfMax");
    }

    @FXML
    void goToUpdate(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../update.fxml"));
        Stage updateDragonByIdStage = ChangeWindowController.changeWindow(content, stage, "Update dragon by id");
        UpdateController.setStage(updateDragonByIdStage);
    }

    @FXML
    void head(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.head(), "Head");
    }

    @FXML
    void removeHead(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.removeHead(), "Remove head");
    }

    @FXML
    void clear(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.clear(), "Clear");
    }

    @FXML
    void goToFilterLessThanAge(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../filterLessThanAge.fxml"));
        Stage filterLessThanAgeStage = ChangeWindowController.changeWindow(content, stage, "Filter less than age");
        FilterLessThanAgeController.setStage(filterLessThanAgeStage);
    }

    @FXML
    void goToFilterContainsName(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../filterContainsName.fxml"));
        Stage filterContainsNameStage = ChangeWindowController.changeWindow(content, stage, "Filter contains name");
        FilterContainsNameController.setStage(filterContainsNameStage);
    }

    @FXML
    void sumOfAge(ActionEvent event) {
        CommandStatusController.showCommandStatus(Client.sumOfAge(), "Dragons");
    }

    @FXML
    void goToRemoveById(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../removeById.fxml"));
        Stage removeStage = ChangeWindowController.changeWindow(content, stage, "Remove by id");
        RemoveByIdController.setStage(removeStage);
    }

    @FXML
    void goToTable(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../dragonTable.fxml"));
        Stage tableStage = ChangeWindowController.changeWindow(content, stage, "Table");
        TableController.setStage(tableStage);
    }

    @FXML
    void goToVisualisation(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../visualisation.fxml"));
        Stage visualisationStage = ChangeWindowController.changeWindow(content, stage, "Visualisation");
        VisualisationController.setStage(visualisationStage);
    }

    @FXML
    void initialize() {
        initLocale();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        menuLabel.setText(setString("menu.label"));
        helpButton.setText(setString("help.button"));
        infoButton.setText(setString("info.button"));
        headButton.setText(setString("head.button"));
        removeHeadButton.setText(setString("removeHead.button"));
        addButton.setText(setString("add.button"));
        addMaxButton.setText(setString("addMax.button"));
        updateButton.setText(setString("update.button"));
        clearButton.setText(setString("clear.button"));
        filterLessThanAgeButton.setText(setString("filterLessThanAge.button"));
        filterContainsNameButton.setText(setString("filterContainsName.button"));
        sumOfAgeButton.setText(setString("sumOfAge.button"));
        removeByIdButton.setText(setString("removeById.button"));
        tableButton.setText(setString("dragonTable.button"));
        visualisationButton.setText(setString("visualisation.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }
}

