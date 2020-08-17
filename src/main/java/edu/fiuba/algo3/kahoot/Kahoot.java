package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.KahootCambioModoEventHandler;
import edu.fiuba.algo3.eventos.kahoot.KahootSalirEventHandler;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.Resources;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Kahoot {

    private final Stage stage;
    private final ArrayList<Pregunta> preguntas;
    private final ArrayList<Jugador> jugadores;
    private final MediaPlayer reproductor;
    private final Media musica;
    private Juego juego;

    public Kahoot(Stage stage) {
        this.stage = stage;
        this.preguntas = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.musica = new Media(new File(Resources.MusicaKahootRuta()).toURI().toString());
        this.reproductor = new MediaPlayer(this.musica);
    }

    public void iniciar() throws PuntoError {
        KahootModo modoPreparacion = new KahootPreparacion(this.stage, this.preguntas, this.jugadores,this.juego);
        KahootModo modoJuego = new KahootJuego(this.stage, this.preguntas, this.jugadores,this.juego);
        KahootModo modoRespuestas = new KahootResultados(this.stage, this.preguntas, this.jugadores,this.juego);

        modoPreparacion.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoJuego,reproductor));
        modoJuego.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoRespuestas,reproductor));
        modoRespuestas.cuandoFinaliceEjecutar(new KahootSalirEventHandler());

        modoPreparacion.iniciar(reproductor);
        this.comenzarMusica();
    }

    public void comenzarMusica(){
        this.reproductor.setOnEndOfMedia(new Runnable() {
            public void run() {
                reproductor.seek(Duration.ZERO);
            }
        });
        this.reproductor.play();
    }
}
