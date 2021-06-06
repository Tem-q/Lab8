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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TableController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        TableController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        TableController.locale = locale;
    }

    ObservableList<String> fields = FXCollections.observableArrayList("Id", "Name", "X coordinate", "Y coordinate", "Creation Date", "Age", "Description", "Weight", "Type", "Killer name", "Killer height", "Killer weight", "X", "Y", "Z", "Location name");
    List<Dragon> dragonsArrayList = new ArrayList<>();

    @FXML
    private TableView<Dragon> table;

    @FXML
    private Label tableLabel;

    @FXML
    private Label fieldLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Button sortButton;

    @FXML
    private Button filterButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button visualisationButton;

    @FXML
    private TextField valuesTextField;

    @FXML
    private ChoiceBox<String> fieldsChoiceBox;

    @FXML
    private TableColumn<Dragon, Long> dragonAgeColumn;

    @FXML
    private TableColumn<Dragon, Float> xColumn;

    @FXML
    private TableColumn<Dragon, String> personNameColumn;

    @FXML
    private TableColumn<Dragon, Long> personWeightColumn;

    @FXML
    private TableColumn<Dragon, Double> y_coordinateColumn;

    @FXML
    private TableColumn<Dragon, DragonType> dragonTypeColumn;

    @FXML
    private TableColumn<Dragon, Float> zColumn;

    @FXML
    private TableColumn<Dragon, Float> personHeightColumn;

    @FXML
    private TableColumn<Dragon, String> dragonNameColumn;

    @FXML
    private TableColumn<Dragon, Integer> dragonWeightColumn;

    @FXML
    private TableColumn<Dragon, String> locationNameColumn;

    @FXML
    private TableColumn<Dragon, Integer> yColumn;

    @FXML
    private TableColumn<Dragon, String> dateColumn;

    @FXML
    private TableColumn<Dragon, Integer> idColumn;

    @FXML
    private TableColumn<Dragon, Long> x_coordinateColumn;

    @FXML
    private TableColumn<Dragon, String> dragonDescriptionColumn;

    @FXML
    private Button updateButton;


    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
        Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
        MenuController.setStage(menuStage);
    }

    @FXML
    void goToVisualisation(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../visualisation.fxml"));
        Stage visualisationStage = ChangeWindowController.changeWindow(content, stage, "Visualisation");
        VisualisationController.setStage(visualisationStage);
    }

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Integer>("id"));
        dragonNameColumn.setCellValueFactory(new PropertyValueFactory<Dragon, String>("name"));
        x_coordinateColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Long>("x_coordinate"));
        y_coordinateColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Double>("y_coordinate"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Dragon, String>("creationDate"));
        dragonAgeColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Long>("age"));
        dragonDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Dragon, String>("description"));
        dragonWeightColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Integer>("weight"));
        dragonTypeColumn.setCellValueFactory(new PropertyValueFactory<Dragon, DragonType>("type"));
        personNameColumn.setCellValueFactory(new PropertyValueFactory<Dragon, String>("killer_name"));
        personHeightColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Float>("killer_height"));
        personWeightColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Long>("killer_weight"));
        xColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Float>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Integer>("y"));
        zColumn.setCellValueFactory(new PropertyValueFactory<Dragon, Float>("z"));
        locationNameColumn.setCellValueFactory(new PropertyValueFactory<Dragon, String>("location_name"));

        initDragons();

        fieldsChoiceBox.setValue("Id");
        fieldsChoiceBox.setItems(fields);

        initLocale();
    }

    private void initDragons() {
        ObservableList<Dragon> dragons = FXCollections.observableArrayList();
        String collection = Client.getCollection();
        String [] collectionArray = collection.split(",");
        try {
            for (int i = 0; i < collection.length(); i+=17) {
                int id = Integer.parseInt(collectionArray[i]);
                String dragonName = collectionArray[i+1];
                long x_coordinate = Long.parseLong(collectionArray[i+2]);
                double y_coordinate = Double.parseDouble(collectionArray[i+3]);
                LocalDate creationDate = LocalDate.parse(collectionArray[i+4]);
                long dragonAge = Long.parseLong(collectionArray[i+5]);
                String description = collectionArray[i+6];
                int dragonWeight = Integer.parseInt(collectionArray[i+7]);
                DragonType dragonType = DragonType.valueOf(collectionArray[i+8]);
                String personName = collectionArray[i+9];
                float personHeight = Float.parseFloat(collectionArray[i+10]);
                long personWeight = Long.parseLong(collectionArray[i+11]);
                float x = Float.parseFloat(collectionArray[i+12]);
                int y = Integer.parseInt(collectionArray[i+13]);
                float z = Float.parseFloat(collectionArray[i+14]);
                String locationName = collectionArray[i+15];

                Dragon dragon = new Dragon(id, dragonName, new Coordinates(x_coordinate, y_coordinate), creationDate, dragonAge, description, dragonWeight, dragonType, new Person(personName, personHeight, personWeight, new Location(x, y, z, locationName)));
                dragons.add(dragon);
                dragonsArrayList.add(dragon);
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        table.setItems(dragons);
    }

    @FXML
    void sort(ActionEvent event) {
        table.getItems().removeAll(dragonsArrayList);
        String valueName = fieldsChoiceBox.getValue();

        ObservableList<Dragon> sortDragons = FXCollections.observableArrayList();
        if (valueName.equals("Id")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getId)).collect(Collectors.toList()));
        }
        if (valueName.equals("Name")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getName)).collect(Collectors.toList()));
        }
        if (valueName.equals("X coordinate")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getX_coordinate)).collect(Collectors.toList()));
        }
        if (valueName.equals("Y coordinate")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getY_coordinate)).collect(Collectors.toList()));
        }
        if (valueName.equals("Creation Date")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getCreationDate)).collect(Collectors.toList()));
        }
        if (valueName.equals("Age")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getAge)).collect(Collectors.toList()));
        }
        if (valueName.equals("Description")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getDescription)).collect(Collectors.toList()));
        }
        if (valueName.equals("Weight")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getWeight)).collect(Collectors.toList()));
        }
        if (valueName.equals("Type")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getType)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer name")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getKiller_name)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer height")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getKiller_height)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer weight")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getKiller_weight)).collect(Collectors.toList()));
        }
        if (valueName.equals("X")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getX)).collect(Collectors.toList()));
        }
        if (valueName.equals("Y")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getY)).collect(Collectors.toList()));
        }
        if (valueName.equals("Z")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getZ)).collect(Collectors.toList()));
        }
        if (valueName.equals("Location name")) {
            sortDragons.addAll(dragonsArrayList.stream().sorted(Comparator.comparing(Dragon::getLocation_name)).collect(Collectors.toList()));
        }
        table.setItems(sortDragons);
    }

    @FXML
    void filter(ActionEvent event) {
        table.getItems().removeAll(dragonsArrayList);
        String valueName = fieldsChoiceBox.getValue();
        String filterValue = valuesTextField.getText();
        ObservableList<Dragon> filterDragons = FXCollections.observableArrayList();
        if (valueName.equals("Id")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getId()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Name")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getName()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("X coordinate")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getX_coordinate()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Y coordinate")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getY_coordinate()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Creation Date")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getCreationDate()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Age")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getAge()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Description")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getDescription()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Weight")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getWeight()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Type")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getType()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer name")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getKiller_name()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer height")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getKiller_height()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Killer weight")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getKiller_weight()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("X")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getX()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Y")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getY()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Z")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getZ()).contains(filterValue)).collect(Collectors.toList()));
        }
        if (valueName.equals("Location name")) {
            filterDragons.addAll(dragonsArrayList.stream().filter(d -> String.valueOf(d.getLocation_name()).contains(filterValue)).collect(Collectors.toList()));
        }
        table.setItems(filterDragons);
    }

    @FXML
    public void update(ActionEvent actionEvent) {
        try {
            Dragon dragon = table.getSelectionModel().getSelectedItem();
            UpdateFromTableController.setAllInit(String.valueOf(dragon.getId()), dragon.getName(), String.valueOf(dragon.getX_coordinate()), String.valueOf(dragon.getY_coordinate()),
                    String.valueOf(dragon.getAge()), String.valueOf(dragon.getDescription()), String.valueOf(dragon.getWeight()), dragon.getType().toString(),
                    String.valueOf(dragon.getKiller_name()), String.valueOf(dragon.getKiller_height()), String.valueOf(dragon.getKiller_weight()),
                    String.valueOf(dragon.getX()), String.valueOf(dragon.getY()), String.valueOf(dragon.getZ()), dragon.getLocation_name());
            Parent content = null;
            try {
                content = FXMLLoader.load(getClass().getResource("../updateFromTable.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage updateFromTableStage = ChangeWindowController.makeWindow(content, "Update dragon");
            UpdateFromTableController.setStage(updateFromTableStage);
        } catch (NullPointerException e) {}
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        tableLabel.setText(setString("table.label"));
        fieldLabel.setText(setString("field.label"));
        valueLabel.setText(setString("value.label"));
        sortButton.setText(setString("sort.button"));
        filterButton.setText(setString("filter.button"));
        updateButton.setText(setString("update.button"));
        menuButton.setText(setString("goToMenu.button"));
        visualisationButton.setText(setString("visualisation.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

}

