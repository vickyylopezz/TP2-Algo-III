package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioLayoutEventHandler;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.escenas.resultados.UltimaLayout;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootResultados extends KahootModo {

    public KahootResultados(Stage stage, Pane panelPadre, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, panelPadre, preguntas, jugadores);
    }

    @Override
    public void iniciar() {
        Juego juego = new Juego(this.preguntas, this.jugadores);
        UltimaLayout finalizacion = new UltimaLayout(this.jugadores, juego);
        finalizacion.eventoSiguiente(this.eventoSalida,"Volver a Jugar");

        new CambioLayoutEventHandler(this.panelPadre, finalizacion).handle(null);
    }
}
