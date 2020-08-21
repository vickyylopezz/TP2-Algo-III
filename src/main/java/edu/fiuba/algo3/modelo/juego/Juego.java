package edu.fiuba.algo3.modelo.juego;

import java.util.*;

public class Juego {

    private final ArrayList<Partida> partidas;
    private Integer indiceParida;

    public Juego(ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        this.partidas = new ArrayList<>();
        for (Pregunta pregunta: preguntas) { this.partidas.add(new Partida(pregunta, jugadores)); }
    }

    public Boolean existePartida() {
        return this.indiceParida != null && this.indiceParida < this.partidas.size();
    }

    public void iniciarPartidas() {
        if (this.indiceParida != null) return;
        this.indiceParida = 0;
    }

    public void siguientePartida() { this.indiceParida++; }

    public Partida obtenerPartida() {
        if (!this.existePartida()) return null;
        return this.partidas.get(this.indiceParida);
    }

    public ArrayList<Jugador> ganador(ArrayList<Jugador> jugadores) {
        if(jugadores.get(0).puntajeTotal().igual(jugadores.get(1).puntajeTotal())){
            return jugadores;
        }
        jugadores.sort((j1, j2) -> j2.puntajeTotal().obtenerValor().compareTo(j1.puntajeTotal().obtenerValor()));
        jugadores.remove(1);
        return jugadores;
    }
}
