package system.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String label;
    private static Stage primaryStage;
    private final String PATH = "login";

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML(PATH));
        stage.setTitle(label);
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        stage.sizeToScene();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent newRoot = loadFXML(fxml);
        scene.setRoot(newRoot);

        Stage stage = (Stage) scene.getWindow();
        stage.sizeToScene();
        stage.centerOnScreen();

        if (scene != null) {
            stage.setTitle(label);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        label = fxml;
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}