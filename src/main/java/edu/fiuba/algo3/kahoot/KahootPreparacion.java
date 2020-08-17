package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.Lector;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.escenas.preparacion.BienvenidaEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorJugadoresEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorPreguntasEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.IniciarJuegoEscena;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class KahootPreparacion extends KahootModo {

    public KahootPreparacion(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);

        this.precargarPreguntas();
        this.crearJugadores();
    }

    @Override
    public void iniciar(MediaPlayer reproductor) {
        BienvenidaEscena bienvenida = new BienvenidaEscena(reproductor);
        EditorPreguntasEscena editorPreguntas = new EditorPreguntasEscena(this.stage, this.preguntas,reproductor);
        EditorJugadoresEscena editorJugadores = new EditorJugadoresEscena(this.jugadores, reproductor);
        IniciarJuegoEscena iniciarJuego = new IniciarJuegoEscena(reproductor);

        bienvenida.eventoBotonPrincipal(new CambioEscenaEventHandler(this.stage, editorJugadores));
        bienvenida.eventoSiguiente(
                new CambioEscenaEventHandler(this.stage, editorPreguntas),
                "Preguntas"
        );

        editorPreguntas.eventoAnterior(new CambioEscenaEventHandler(this.stage, bienvenida));
        editorPreguntas.eventoSiguiente(
                new CambioEscenaEventHandler(this.stage, editorJugadores),
                "Jugadores"
        );

        editorJugadores.eventoAnterior(new CambioEscenaEventHandler(this.stage, bienvenida));
        editorJugadores.eventoSiguiente(new CambioEscenaEventHandler(this.stage, iniciarJuego));

        iniciarJuego.eventoAnterior(new CambioEscenaEventHandler(this.stage, editorJugadores));
        iniciarJuego.eventoBotonPrincipal(this.eventoSalida);

        new CambioEscenaEventHandler(this.stage, bienvenida).handle(null);
    }

    private void crearJugadores() {
        String nombreJugadorDefault = "";

        this.jugadores.add(new Jugador(nombreJugadorDefault));
        this.jugadores.add(new Jugador(nombreJugadorDefault));
    }

    private void precargarPreguntas() {
        Lector lector = new Lector();
        try {
            lector.extraerPreguntas(new File(Resources.RutaDataPreguntas()));
        } catch (IOException | PreguntaError e) {
            e.printStackTrace();
        }

        this.preguntas.addAll(lector.obtenerPreguntas());
    }
}
