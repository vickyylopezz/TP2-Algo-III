package edu.fiuba.algo3.eventos.preparacion.editorJugadores;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.escenas.preparacion.EditorJugadoresEscena;

import java.util.ArrayList;

public class ActualizadorVistaRegistroJugadores implements JugadorObservador {

    private final ArrayList<Jugador> jugadores;
    private final EditorJugadoresEscena vista;

    public ActualizadorVistaRegistroJugadores(ArrayList<Jugador> jugadores, EditorJugadoresEscena vista) {
        this.jugadores = jugadores;
        this.vista = vista;
    }

    @Override
    public void nuevoCambio(Jugador jugador, JugadorObservable jugadorObservable) {
        if (this.nombreDeJugadoresValidos()) this.vista.habilitarEscenaSigueinte();
        else this.vista.deshabilitarEscenaSigueinte();
    }

    private boolean nombreDeJugadoresValidos() {
        for (Jugador jugador: this.jugadores) {
            if (!this.nombreJugadorValido(jugador)) return false;
        }
        return true;
    }

    private boolean nombreJugadorValido(Jugador jugador) {
        return !jugador.nombre().isBlank() && !jugador.nombre().isEmpty();
    }
}
