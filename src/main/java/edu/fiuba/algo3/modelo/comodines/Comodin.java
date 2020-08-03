package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public abstract class Comodin {
    private final int factor;
    private Jugador jugador;

    public Comodin(int factor) throws ComodinError {
        if (factor == 0)  {
            throw new ComodinError(" Factor del multiplicador nulo invalido ");
        }
        if (factor < 0) {
            throw new ComodinError(" Factor del multiplicador negativo invalido ");
        }
        this.factor = factor;
    }

    public int factor() {
        return this.factor;
    }
    abstract void aplicarARespuesta(Respuesta respuesta) throws ComodinError;

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
}
