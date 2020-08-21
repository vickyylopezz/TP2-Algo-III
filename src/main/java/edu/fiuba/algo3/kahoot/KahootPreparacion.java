package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.comodines.Exclusividad;
import edu.fiuba.algo3.modelo.comodines.Multiplicador;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.Lector;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.escenas.preparacion.BienvenidaLayout;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorJugadoresLayout;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorPreguntasLayout;
import edu.fiuba.algo3.vista.escenas.preparacion.IniciarJuegoLayout;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class KahootPreparacion extends KahootModo {

    public KahootPreparacion(Stage stage, Pane panelPadre, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, panelPadre, preguntas, jugadores);

        this.precargarPreguntas();
        this.crearJugadores();
        this.agregarComodines();
    }

    @Override
    public void iniciar() {
        BienvenidaLayout bienvenida = new BienvenidaLayout();
        EditorPreguntasLayout editorPreguntas = new EditorPreguntasLayout(this.stage, this.preguntas);
        EditorJugadoresLayout editorJugadores = new EditorJugadoresLayout(this.jugadores);
        IniciarJuegoLayout iniciarJuego = new IniciarJuegoLayout();

        bienvenida.eventoBotonPrincipal(new CambioEscenaEventHandler(this.panelPadre, editorJugadores));
        bienvenida.eventoSiguiente(
                new CambioEscenaEventHandler(this.panelPadre, editorPreguntas),
                "Preguntas"
        );

        editorPreguntas.eventoAnterior(new CambioEscenaEventHandler(this.panelPadre, bienvenida));
        editorPreguntas.eventoSiguiente(new CambioEscenaEventHandler(this.panelPadre, editorJugadores));

        editorJugadores.eventoAnterior(new CambioEscenaEventHandler(this.panelPadre, bienvenida));
        editorJugadores.eventoSiguiente(new CambioEscenaEventHandler(this.panelPadre, iniciarJuego));

        iniciarJuego.eventoAnterior(new CambioEscenaEventHandler(this.panelPadre, editorJugadores));
        iniciarJuego.eventoBotonPrincipal(this.eventoSalida);

        new CambioEscenaEventHandler(this.panelPadre, bienvenida).handle(null);
    }

    private void agregarComodines() {
        for(Jugador jugador: this.jugadores) {
            try {
                jugador.agregarComodin(new Multiplicador(2));
                jugador.agregarComodin(new Multiplicador(3));
                jugador.agregarComodin(new Exclusividad(2));
                jugador.agregarComodin(new Exclusividad(2));
            } catch (ComodinError comodinError) { comodinError.printStackTrace(); }
        }
    }

    private void crearJugadores() {
        String nombreJugadorDefault = "";

        this.jugadores.add(new Jugador(nombreJugadorDefault));
        this.jugadores.add(new Jugador(nombreJugadorDefault));
    }

    private void precargarPreguntas() {
        ArrayList<Pregunta> preguntasNuevas;

        Lector lector = new Lector();
        try {
            preguntasNuevas = lector.extraerPreguntas(
                    new File(Resources.RutaDataPreguntas())
            );
        } catch (LectorError | PreguntaError e) {
            e.printStackTrace();
            return;
        }

        this.preguntas.addAll(preguntasNuevas);
        Collections.shuffle(this.preguntas);
    }
}
