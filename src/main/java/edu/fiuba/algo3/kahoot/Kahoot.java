package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.*;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.Resources;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Kahoot {

    private final Stage stage;
    private final ArrayList<Pregunta> preguntas;
    private final ArrayList<Jugador> jugadores;

    public Kahoot(Stage stage) {
        this.stage = stage;
        this.preguntas = new ArrayList<>();
        this.jugadores = new ArrayList<>();
    }

    public void iniciar() {
        KahootModo modoPreparacion = new KahootPreparacion(this.stage, this.preguntas, this.jugadores);
        KahootModo modoJuego = new KahootJuego(this.stage, this.preguntas, this.jugadores);
        KahootModo modoRespuestas = new KahootResultados(this.stage, this.preguntas, this.jugadores);

        modoPreparacion.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoJuego));
        modoJuego.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoRespuestas));
        modoRespuestas.cuandoFinaliceEjecutar(new KahootSalirEventHandler());

        modoPreparacion.iniciar();
    }

    public static void comenzarMusica(){
        Media sonido = new Media(new File(Resources.MusicaKahootRuta()).toURI().toString());
        MediaPlayer reproductor = new MediaPlayer(sonido);
        reproductor.play();
    }
}
