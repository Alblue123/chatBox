package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Messenger");

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/gpt.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 373, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
