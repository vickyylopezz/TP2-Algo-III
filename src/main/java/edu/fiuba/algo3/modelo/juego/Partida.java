package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;

public class Partida {

    private final ArrayList<Jugada> jugadas;
    private Integer turno;

    public Partida(Pregunta pregunta, ArrayList<Jugador> jugadores) {
        this.jugadas = this.crearJugadas(pregunta, jugadores);
        this.turno = -1;
    }

    private ArrayList<Jugada> crearJugadas(Pregunta pregunta, ArrayList<Jugador> jugadores) {
        ArrayList<Jugada> todasJugadas = new ArrayList<>();
        for (Jugador jugador: jugadores) todasJugadas.add(new Jugada(pregunta, jugador));
        return todasJugadas;
    }

    private Boolean turnosFinalizados() { return this.turno >= this.jugadas.size(); }
    
    public Boolean siguienteTurno() {
        this.turno++;
        return !this.turnosFinalizados();
    }

    public Jugada obtenerJugada() {
        if (this.turnosFinalizados()) return null;
        return this.jugadas.get(this.turno);
    }
}
