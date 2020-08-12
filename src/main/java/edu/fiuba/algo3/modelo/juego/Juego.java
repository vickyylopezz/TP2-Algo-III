package edu.fiuba.algo3.modelo.juego;

import java.util.ArrayList;

public class Juego {

    private final ArrayList<Partida> partidas;
    private Integer turno;

    public Juego(ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        this.partidas = new ArrayList<>();
        for (Pregunta pregunta: preguntas) { this.partidas.add(new Partida(pregunta, jugadores)); }
    }

    public Boolean existePartida() {
        return this.turno != null && this.turno < this.partidas.size();
    }

    public void iniciarPartidas() {
        if (this.turno != null) return;
        this.turno = 0;
    }

    public void siguientePartida() { this.turno++; }

    public Partida obtenerPartida() {
        if (!this.existePartida()) return null;
        return this.partidas.get(this.turno);
    }
}
