package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.escenas.preparacion.BienvenidaEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorJugadoresEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorPreguntasEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.IniciarJuegoEscena;
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

        bienvenida.eventoBotonPrincipal(new CambioEscenaEventHandler(this.stage, editorJugadores));
        bienvenida.eventoSiguiente(new CambioEscenaEventHandler(this.stage, editorPreguntas));

        editorPreguntas.eventoAnterior(new CambioEscenaEventHandler(this.stage, bienvenida));
        editorPreguntas.eventoSiguiente(new CambioEscenaEventHandler(this.stage, editorJugadores));

        editorJugadores.eventoAnterior(new CambioEscenaEventHandler(this.stage, bienvenida));
        editorJugadores.eventoSiguiente(new CambioEscenaEventHandler(this.stage, iniciarJuego));

        iniciarJuego.eventoAnterior(new CambioEscenaEventHandler(this.stage, editorJugadores));
        iniciarJuego.eventoBotonPrincipal(this.eventoSalida);

        new CambioEscenaEventHandler(this.stage, bienvenida).handle(null);
    }
}
