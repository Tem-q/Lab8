package Controllers;

import client.Client;
import dragon.*;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class VisualisationController {
    private static Stage stage;
    private static Locale locale;
    private ResourceBundle rb;

    public static void setStage(Stage stage) {
        VisualisationController.stage = stage;
    }

    public static void setLocale(Locale locale) {
        VisualisationController.locale = locale;
    }

    @FXML
    private Label visualisationLabel;

    @FXML
    private Button tableButton;

    @FXML
    private GridPane paneForDragons;

    @FXML
    private Button menuButton;

    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../menu.fxml"));
        Stage menuStage = ChangeWindowController.changeWindow(content, stage, "Menu");
        MenuController.setStage(menuStage);
    }

    @FXML
    void goToTable(ActionEvent event) throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("../dragonTable.fxml"));
        Stage tableStage = ChangeWindowController.changeWindow(content, stage, "Table");
        TableController.setStage(tableStage);
    }

    private void initDragons() {
        paneForDragons.getChildren().clear();

        String collection = Client.getCollection();
        String [] collectionArray = collection.split(",");
        List<Dragon> dragons = new ArrayList<>();
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
                String user = collectionArray[i+16];

                Dragon dragon = new Dragon(id, dragonName, new Coordinates(x_coordinate, y_coordinate), creationDate, dragonAge, description, dragonWeight, dragonType, new Person(personName, personHeight, personWeight, new Location(x, y, z, locationName)));
                dragon.setUser(user);
                dragons.add(dragon);
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        List<ViewDragon> viewDragons = getPositions(dragons);
        String[] usersArray = Client.fillUsers().split(",");
        List<String> users = new ArrayList<>(Arrays.asList(usersArray));
        List<Color> colors = makeColorArrayList(users);

        for (int i = 0; i < viewDragons.size(); i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX(20);
            rectangle.setY(10);
            rectangle.setWidth(100);
            rectangle.setHeight(50);
            int finalI = i;

            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragon dragon = viewDragons.get(finalI).getDragon();
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
                }
            });


            Color color = setColor(viewDragons.get(i), users, colors);//Color.CORAL;//Color.web("#" + hex);
            rectangle.setFill(color);
            Text text = new Text(viewDragons.get(i).getDragon().getName());
            text.setFill(Color.WHITE);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000));
            scaleTransition.setNode(rectangle);
            scaleTransition.setCycleCount(Animation.INDEFINITE);
            scaleTransition.setByX(0.25);
            scaleTransition.setByY(0.25);
            scaleTransition.setAutoReverse(true);
            scaleTransition.play();
            StackPane pane = new StackPane();
            pane.getChildren().addAll(rectangle, text);
            paneForDragons.add(pane, viewDragons.get(i).getX(), viewDragons.get(i).getY());
        }

    }

    @FXML
    void initialize() {
        initLocale();
        initDragons();
    }

    private void initLocale() {
        rb = ResourceBundle.getBundle("text", locale);

        visualisationLabel.setText(setString("visualisation.label"));
        menuButton.setText(setString("goToMenu.button"));
        tableButton.setText(setString("dragonTable.button"));
    }

    private String setString(String text){
        String stringToSet = rb.getString(text);
        stringToSet = new String(stringToSet.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return stringToSet;
    }

    private List<ViewDragon> getPositions(List<Dragon> dragons) {
        List<Dragon> sortX = dragons.stream().sorted((d1, d2) -> Long.compare(d1.getX_coordinate(), d2.getX_coordinate())).collect(Collectors.toList());
        List<Dragon> sortY = dragons.stream().sorted((d1, d2) -> Double.compare(d1.getY_coordinate(), d2.getY_coordinate())).collect(Collectors.toList());
        List<ViewDragon> view = new ArrayList<>();
        for (int i = 0; i < dragons.size(); i++) {
            Dragon dragon = dragons.get(i);
            view.add(new ViewDragon(find(sortX, dragon), find(sortY, dragon), dragon));
        }
        return view;
    }

    private int find(List<Dragon> dragons, Dragon dragon) {
        for (int i = 0; i < dragons.size(); i++) {
            if (dragons.get(i).equals(dragon)) return i;
        }
        return 0;
    }

    class ViewDragon {
        int x;
        int y;
        Dragon dragon;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public ViewDragon(int x, int y, Dragon dragon) {
            this.x = x;
            this.y = y;
            this.dragon = dragon;
        }

        public Dragon getDragon() {
            return dragon;
        }
    }

    private Color setColor(ViewDragon viewDragon, List<String> users, List<Color> colors) {
        for (int i = 0; i < users.size()-1; i++) {
            if (viewDragon.getDragon().getUser().equals(users.get(i))) {
                return colors.get(i);
            }
        }
        return null;
    }

    private List<Color> makeColorArrayList(List<String> users) {
        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            colors.add(new Color(Math.random(), Math.random(), Math.random(), 1));
        }
        return colors;
    }

}

