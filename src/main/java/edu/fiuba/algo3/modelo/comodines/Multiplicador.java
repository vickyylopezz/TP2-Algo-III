package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Multiplicador extends Comodin{
    private int factor = 0;

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if (!jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.agregarComodin(this);
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError {
        if(!this.esValido(unaRespuesta)){
            throw new ComodinError(this.toString() + "no se encuentra dentro de los comodines del jugador");
        }
        if(!this.esValido(otraRespuesta)){
            throw new ComodinError(this.toString() + "no se encuentra dentro de los comodines del jugador");
        }
        unaRespuesta.agregarComodin(this);
        otraRespuesta.agregarComodin(this);
    }

    boolean esValido(Respuesta unaRespuesta) throws ComodinError {
        if(unaRespuesta == null){
            throw new ComodinError("Respuesta invalida");
        }
        return (this.jugador == unaRespuesta.obtenerJugador());
    }

}
