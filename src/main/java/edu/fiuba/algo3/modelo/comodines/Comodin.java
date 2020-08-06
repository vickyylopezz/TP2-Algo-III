package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public abstract class Comodin {
    private final int factor;
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

    public void definirJugador(Jugador jugador) throws ComodinError {
        if(jugador == null){
            throw new ComodinError("Jugador invalido");
        }
        this.jugador = jugador;
    }

    public Jugador obtenerJugador() {
        return this.jugador;
    }

    public abstract void validarPregunta(Jugada jugada) throws ComodinError, JugadorError;
    abstract void aplicarARespuestas(Respuesta unaRespuesta,Respuesta otraRespuesta) throws ComodinError, RespuestaError, JugadorError;

    public Puntaje puntajeNuevo(ArrayList<Punto> puntos) throws ComodinError {
        if(puntos == null){
            throw new ComodinError("Coleccion de puntos invalida");
        }
        Puntaje puntaje = new Puntaje();
        for(Punto punto: puntos){
            puntaje.agregarPunto(punto.modificarValor(this.factor));
        }
        return puntaje;
    }

    public Punto puntoNuevo(Punto punto) throws ComodinError {
        if(punto == null){
            throw new ComodinError("Punto nulo invalido");
        }
        return punto.modificarValor(this.factor);
    }
}
