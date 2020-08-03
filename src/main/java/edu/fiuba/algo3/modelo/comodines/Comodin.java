package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Respuesta;

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

    public abstract void validarPregunta(Jugada jugada) throws ComodinError;
    abstract void aplicarARespuestas(Respuesta unaRespuesta,Respuesta otraRespuesta) throws ComodinError, RespuestaError;

    boolean esValido(Respuesta unaRespuesta) throws ComodinError {
        if(unaRespuesta == null){
            throw new ComodinError(unaRespuesta.toString() + "invalida");
        }
        return (this.jugador == unaRespuesta.obtenerJugador());
    }
}
