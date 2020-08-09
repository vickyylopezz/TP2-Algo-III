package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.escenas.CargarPreguntasVista;
import edu.fiuba.algo3.vista.escenas.Escena;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Escena cargadorPreguntas = new CargarPreguntasVista();

        stage.setScene(cargadorPreguntas.obtenerEscena());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}