package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.ArrayList;

public abstract class KahootModo {

    protected final Stage stage;
    protected final Pane panelPadre;
    protected final ArrayList<Pregunta> preguntas;
    protected final ArrayList<Jugador> jugadores;
    protected EventHandler<ActionEvent> eventoSalida;

    public KahootModo(Stage stage, Pane panelPadre, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        this.stage = stage;
        this.panelPadre = panelPadre;
        this.preguntas = preguntas;
        this.jugadores = jugadores;
    }

    public void cuandoFinaliceEjecutar(EventHandler<ActionEvent> eventoDeSalida) {
        this.eventoSalida = eventoDeSalida;
    }

    abstract public void iniciar();
}
