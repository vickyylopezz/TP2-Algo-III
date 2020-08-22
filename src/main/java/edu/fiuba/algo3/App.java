package edu.fiuba.algo3;

import edu.fiuba.algo3.kahoot.Kahoot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage)  {
        Kahoot app = new Kahoot(stage);
        app.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}