package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.escenas.CargarPreguntasVista;
import edu.fiuba.algo3.vista.escenas.RegistrarJugadoresVista;
import edu.fiuba.algo3.vista.escenas.Escena;
import edu.fiuba.algo3.vista.escenas.IniciarJuegoVista;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        //Escena iniciarJuego = new IniciarJuegoVista();
        Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        //stage.setScene(registrarJugadores.obtenerEscena());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}