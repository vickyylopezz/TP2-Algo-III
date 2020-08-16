package edu.fiuba.algo3.eventos.preparacion.editorJugadores;

import edu.fiuba.algo3.modelo.juego.Jugador;

import java.util.ArrayList;

public class JugadorObservable {

    private final Jugador jugador;
    private final ArrayList<JugadorObservador> observadores;

    public JugadorObservable(Jugador jugador) {
        this.jugador = jugador;
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(JugadorObservador observador) {
        this.observadores.add(observador);
    }

    public void eliminarObservador(JugadorObservador observador) {
        this.observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (JugadorObservador observador: this.observadores) {
            observador.nuevoCambio(this.jugador, this);
        }
    }

    public void cambiarNombre(String nuevoNombre) {
        this.jugador.cambiarNombre(nuevoNombre);
        this.notificarObservadores();
    }

    public String nombre() { return this.jugador.nombre(); }
}
