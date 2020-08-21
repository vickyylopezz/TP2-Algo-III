package edu.fiuba.algo3;

import edu.fiuba.algo3.kahoot.Kahoot;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setMinHeight(800);
        stage.setMinWidth(1280);

        Kahoot app = new Kahoot(stage);
        app.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}