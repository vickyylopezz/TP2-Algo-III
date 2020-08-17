package edu.fiuba.algo3;

import edu.fiuba.algo3.kahoot.Kahoot;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws PuntoError {
        Kahoot app = new Kahoot(stage);
        app.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}