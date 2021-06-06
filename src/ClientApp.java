import Controllers.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent content = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene = new Scene(content);

        StartController.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dragons");
        primaryStage.show();
    }
}
