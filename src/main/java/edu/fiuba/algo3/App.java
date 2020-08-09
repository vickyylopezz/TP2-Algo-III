package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.escenas.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        Escena registrarJugadores = new RegistrarJugadoresVista();

        //stage.setScene(crearJuego.obtenerEscena());
        //stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        stage.setScene(registrarJugadores.obtenerEscena());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}