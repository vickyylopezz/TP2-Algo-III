package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.CargarPreguntaControlador;
import edu.fiuba.algo3.controladores.IniciarJuegoControlador;
import edu.fiuba.algo3.controladores.RegistrarJugadoresControlador;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;

import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.escenas.*;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, PreguntaError {
        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        //Escena puntosobtenidos = new PuntosObtenidosVista();
        //Escena ganador = new GanadorVista();

        //Jugada jugada = new Jugada(new VerdaderoFalsoConPenalidad("Estamos en el año 2021"), new Jugador("Carlos"));
        //Escena previaPregunta = new PreviaPreguntaVista(jugada);

        //stage.setScene(crearJuego.obtenerEscena());
        //stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        //stage.setScene(registrarJugadores.obtenerEscena());
        //stage.setScene(puntosobtenidos.obtenerEscena());
        //stage.setScene(previaPregunta.obtenerEscena());
        //stage.setScene(ganador.obtenerEscena());
        //stage.show();

        //CargarPreguntaControlador preguntasControlador = new CargarPreguntaControlador(stage);
        //preguntasControlador.mostrarVista();

        // para probar muestreo de preguntas
        File file = new File("src/main/resources/Preguntas2.json");
        Lector lector = new Lector();
        lector.extraerPreguntas(file);

        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1); jugadores.add(jugador2);

        ArrayList<Pregunta> preguntas = lector.obtenerPreguntas();

        ConstructorEscenas constructor = new ConstructorEscenas(jugadores, lector.obtenerPreguntas());
        Scene escena = constructor.crearEscena();
        stage.setScene(escena);
        stage.show();




        //IniciarJuegoControlador iniciarJuegoControlador = new IniciarJuegoControlador(stage);
        //iniciarJuegoControlador.mostrarVista();
    }

    public static void main(String[] args) {
        launch();
    }

}