package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeIgualError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

import java.util.ArrayList;

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

    public Jugador ganador(Jugador jugador1, Jugador jugador2) throws PuntoError {
        if(jugador1.puntajeTotal().mayor(jugador2.puntajeTotal())){
            return jugador1;
        }else if(!jugador1.puntajeTotal().mayor(jugador2.puntajeTotal())){
            return jugador2;
        }else{
            throw new PuntajeIgualError();
        }
    }
}
