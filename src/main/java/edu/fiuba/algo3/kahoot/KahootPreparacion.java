package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.CambioLayoutEventHandler;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.layouts.preparacion.BienvenidaEscena;
import edu.fiuba.algo3.vista.layouts.preparacion.EditorJugadoresEscena;
import edu.fiuba.algo3.vista.layouts.preparacion.EditorPreguntasEscena;
import edu.fiuba.algo3.vista.layouts.preparacion.IniciarJuegoEscena;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootPreparacion extends KahootModo {

    public KahootPreparacion(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);
    }

    @Override
    public void iniciar() {
        BienvenidaEscena bienvenida = new BienvenidaEscena();
        EditorPreguntasEscena editorPreguntas = new EditorPreguntasEscena();
        EditorJugadoresEscena editorJugadores = new EditorJugadoresEscena();
        IniciarJuegoEscena iniciarJuego = new IniciarJuegoEscena();

        bienvenida.eventoBotonPrincipal(new CambioLayoutEventHandler(this.stage, editorJugadores));
        bienvenida.eventoSiguiente(new CambioLayoutEventHandler(this.stage, editorPreguntas));

        editorPreguntas.eventoAnterior(new CambioLayoutEventHandler(this.stage, bienvenida));
        editorPreguntas.eventoSiguiente(new CambioLayoutEventHandler(this.stage, editorJugadores));

        editorJugadores.eventoAnterior(new CambioLayoutEventHandler(this.stage, bienvenida));
        editorJugadores.eventoSiguiente(new CambioLayoutEventHandler(this.stage, iniciarJuego));

        iniciarJuego.eventoAnterior(new CambioLayoutEventHandler(this.stage, editorJugadores));
        iniciarJuego.eventoBotonPrincipal(this.eventoSalida);

        new CambioLayoutEventHandler(this.stage, bienvenida).handle(null);
    }
}
