package edu.fiuba.algo3.eventos.preparacion.editorJugadores;

import edu.fiuba.algo3.modelo.juego.Jugador;

public interface JugadorObservador {
    void nuevoCambio(Jugador jugador, JugadorObservable jugadorObservable);
}
