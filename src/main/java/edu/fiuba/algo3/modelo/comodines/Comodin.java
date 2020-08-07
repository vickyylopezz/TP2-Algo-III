package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public abstract class Comodin {
    protected int factor;
    protected Jugador jugador;

    public Comodin(int factor) throws ComodinError {
        if (factor == 0)  {
            throw new ComodinError(" Factor del multiplicador nulo invalido ");
        }
        if (factor < 0) {
            throw new ComodinError(" Factor del multiplicador negativo invalido ");
        }
        this.factor = factor;
    }

    public int obtenerFactor() {
        return this.factor;
    }

    public Jugador obtenerJugador() {
        return this.jugador;
    }

    public abstract void validarPregunta(Jugada jugada) throws ComodinError, JugadorError;

    public abstract void validarJugada(Jugada jugada) throws ComodinError, JugadorError;

    abstract void aplicarARespuestas(Respuesta unaRespuesta,Respuesta otraRespuesta) throws ComodinError, RespuestaError, JugadorError;
    abstract void aplicarARespuestas(ArrayList<Respuesta> respuestas) throws ComodinError, RespuestaError, JugadorError;

    public void definirJugador(Jugador jugador) throws ComodinError {
        if(jugador == null){
            throw new ComodinError("Jugador invalido");
        }
        this.jugador = jugador;
    }

    public Puntaje puntajeNuevo(ArrayList<Punto> puntos){
        Puntaje puntaje = new Puntaje();
        for(Punto punto: puntos){
            puntaje.agregarPunto(punto.modificarValor(this.factor));
        }
        return puntaje;
    }
}
