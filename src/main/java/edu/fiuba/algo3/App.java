package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;

import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.escenas.*;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException, PreguntaError {
        new Lector().extraerPreguntas();

        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        Escena puntosobtenidos = new PuntosObtenidosVista();
        //Escena ganador = new GanadorVista();

        //Jugada jugada = new Jugada(new VerdaderoFalsoConPenalidad("Estamos en el año 2021"), new Jugador("Carlos"));
        //Escena previaPregunta = new PreviaPreguntaVista(jugada);

        //stage.setScene(crearJuego.obtenerEscena());
        //stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        //stage.setScene(registrarJugadores.obtenerEscena());
        stage.setScene(puntosobtenidos.obtenerEscena());
        //stage.setScene(previaPregunta.obtenerEscena());
        //stage.setScene(ganador.obtenerEscena());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}