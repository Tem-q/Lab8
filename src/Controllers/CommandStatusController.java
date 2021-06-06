package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandStatusController {

    public static void showCommandStatus(String label, String title) {
        Label lbl = new Label(label);

        Button btn = new Button("Ok");
        btn.setPrefWidth(70);
        btn.setPrefHeight(30);

        VBox root = new VBox(lbl, btn);
        VBox.setMargin(lbl, new Insets(30, 30, 30, 30));
        VBox.setMargin(btn, new Insets(0, 30, 30, 30));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setMinWidth(100);
        stage.setMinHeight(100);
        stage.setScene(scene);
        stage.setTitle(title);
        btn.setOnAction(event -> stage.close());
        stage.show();
    }
}
