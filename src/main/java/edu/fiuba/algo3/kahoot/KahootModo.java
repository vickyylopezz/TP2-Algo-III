package edu.fiuba.algo3.kahoot;


import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.ArrayList;

public abstract class KahootModo {

    protected final Stage stage;
    protected final ArrayList<Pregunta> preguntas;
    protected final ArrayList<Jugador> jugadores;
    protected EventHandler<ActionEvent> eventoSalida;
    protected Juego juego;

    public KahootModo(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores, Juego juego) {
        this.stage = stage;
        this.preguntas = preguntas;
        this.jugadores = jugadores;
        this.juego = juego;
    }

    public void cuandoFinaliceEjecutar(EventHandler<ActionEvent> eventoDeSalida) {
        this.eventoSalida = eventoDeSalida;
    }

    abstract public void iniciar(MediaPlayer reproductor) throws PuntoError;
}
