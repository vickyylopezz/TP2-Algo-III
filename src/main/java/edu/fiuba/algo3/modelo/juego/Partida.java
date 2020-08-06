package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.PartidaError;

import java.util.ArrayList;

public class Partida {

    private final Pregunta pregunta;
    private final ArrayList<Jugador> jugadores;
    private final ArrayList<Jugada> jugadas;
    private Integer turno;

    public Partida(Pregunta pregunta, ArrayList<Jugador> jugadores) {
        this.pregunta = pregunta;
        this.jugadores = jugadores;
        this.jugadas = this.crearJugadas();
        this.turno = -1;
    }

    public Pregunta obtenerPregunta() { return this.pregunta; }

    public ArrayList<Jugador> obtenerJugadores() { return this.jugadores; }

    private ArrayList<Jugada> crearJugadas() {
        ArrayList<Jugada> todasJugadas = new ArrayList<>();
        for (Jugador jugador: this.jugadores) {
            todasJugadas.add(new Jugada(this.pregunta, jugador));
        }
        return todasJugadas;
    }

    private boolean turnosNoIniciados() { return this.turno < 0; }

    private Boolean turnosFinalizados() { return this.turno >= this.jugadas.size(); }

    private ArrayList<Respuesta> respuestasJugadas() {
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        for (Jugada jugada: this.jugadas) {
            respuestas.add(jugada.obtenerRespuesta());
        }
        return respuestas;
    }

    private ArrayList<Comodin> comodinesJugadas() {
        ArrayList<Comodin> comodines = new ArrayList<>();
        for (Jugada jugada: this.jugadas) {
            comodines.add(jugada.comodinSeleccionado());
        }
        return comodines;
    }

    /* Leer linea 280 de PartidaTest antes de descomentar
    private void aplicarComodines(ArrayList<Comodin> comodines, ArrayList<Respuesta> respuestas) {
        for (Comodin comodin: comodines) {

            // refactoriazacion que aplicarARespuestaSeaConUnArreglo
            // y sin lanzar excepcion no es necesario ninguna verificacion en este momento

            try { comodin.aplicarARespuestas(respuestas.get(0), respuestas.get(1)); }
            catch (Exception err) { err.printStackTrace(); }
        }
    }*/

    public Boolean siguienteTurno() {
        this.turno++;
        return !this.turnosFinalizados();
    }

    public Jugada obtenerJugada() {
        if (this.turnosNoIniciados() || this.turnosFinalizados()) return null;
        return this.jugadas.get(this.turno);
    }

    public ArrayList<Respuesta> obtenerRespuestas() throws PartidaError {
        if (!this.turnosFinalizados()) throw new PartidaError("Turnos no finalizados");

        ArrayList<Respuesta> respuestas = this.respuestasJugadas();
        ArrayList<Comodin> comodines = this.comodinesJugadas();

        // Leer linea 280 de PartidaTest antes de descomentar.
        // this.aplicarComodines(comodines, respuestas);

        return respuestas;
    }
}
