package Controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeWindowController {
    public static Stage changeWindow(Parent content, Stage stage, String title) {
        Scene scene = new Scene(content);
        Stage newStage = new Stage();
        newStage.setMinHeight(50);
        newStage.setMinWidth(50);
        newStage.setScene(scene);
        stage.close();
        newStage.setTitle(title);
        newStage.show();
        return newStage;
    }

    public static Stage makeWindow(Parent content, String title) {
        Scene scene = new Scene(content);
        Stage newStage = new Stage();
        newStage.setMinHeight(50);
        newStage.setMinWidth(50);
        newStage.setScene(scene);
        newStage.setTitle(title);
        newStage.show();
        return newStage;
    }
}
