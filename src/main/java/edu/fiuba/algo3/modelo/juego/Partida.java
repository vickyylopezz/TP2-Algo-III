package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

import java.util.ArrayList;

public class Partida {

    private final ArrayList<Jugada> jugadas;
    private Integer indiceJugada;

    public Partida(Pregunta pregunta, ArrayList<Jugador> jugadores) {
        this.jugadas = new ArrayList<>();
        for (Jugador jugador: jugadores) this.jugadas.add(new Jugada(pregunta, jugador));
    }

    public void iniciarTurnos() {
        if (this.indiceJugada != null) return;
        this.indiceJugada = 0;
    }

    public Boolean existeTurno() {
        return this.indiceJugada != null && this.indiceJugada < this.jugadas.size();
    }

    public void siguienteTurno() { this.indiceJugada++; }

    public Jugada obtenerJugada() {
        if (!this.existeTurno()) return null;
        return this.jugadas.get(this.indiceJugada);
    }

    public void finalizarTurnos() throws PuntoError {
        if (this.existeTurno()) return;
        aplicarComodines();
        agregarRespuestas();
    }

    private void agregarRespuestas() {
        for (Jugada jugada: this.jugadas) {
            jugada.obtenerJugador().agregarRespuesta(jugada.obtenerRespuesta());
        }
    }

    private void aplicarComodines() throws PuntoError {
        ArrayList<Respuesta> respuestas = this.respuestasJugadas();

        for (Comodin comodin: this.comodinesJugadas()) {
            comodin.aplicarARespuestas(respuestas);
            comodin.obtenerJugador().sacarComodin(comodin);
        }
    }

    private ArrayList<Comodin> comodinesJugadas() {
        ArrayList<Comodin> comodines = new ArrayList<>();

        for (Jugada jugada: this.jugadas) {
            if (jugada.comodinSeleccionado() == null) continue;
            comodines.add(jugada.comodinSeleccionado());
        }

        return comodines;
    }

    private ArrayList<Respuesta> respuestasJugadas() {
        ArrayList<Respuesta> respuestas = new ArrayList<>();

        for (Jugada jugada: this.jugadas) {
            respuestas.add(jugada.obtenerRespuesta());
        }

        return respuestas;
    }
}
