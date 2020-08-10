package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Partida {

    private final ArrayList<Jugada> jugadas;
    private Integer turno;

    public Partida(Pregunta pregunta, ArrayList<Jugador> jugadores) {
        this.jugadas = new ArrayList<>();
        for (Jugador jugador: jugadores) this.jugadas.add(new Jugada(pregunta, jugador));
    }

    public void iniciarTurnos() {
        if (this.turno != null) return;
        this.turno = 0;
    }

    public Boolean existeTurno() { return this.turno != null && this.turno < this.jugadas.size(); }

    public void siguienteTurno() { this.turno++; }

    public Jugada obtenerJugada() {
        if (!this.existeTurno()) return null;
        return this.jugadas.get(this.turno);
    }

    public void finalizarTurnos() {
        if (this.existeTurno()) return;
        agregarRespuestas();
        aplicarComodines();
    }

    private void agregarRespuestas() {
        for (Jugada jugada: this.jugadas) {
            jugada.obtenerJugador().agregarRespuesta(jugada.obtenerRespuesta());
        }
    }

    private void aplicarComodines() {
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
