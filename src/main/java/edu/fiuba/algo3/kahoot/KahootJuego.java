package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootJuego extends KahootModo {

    public KahootJuego(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);
    }

    @Override
    public void iniciar() {

    }
}
