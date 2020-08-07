package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.comodin.AplicacionDeComodinInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.comodin.RespuestaInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Multiplicador extends Comodin{
    private int factor = 0;

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    boolean esValido(Respuesta unaRespuesta) throws ComodinError {
        if(unaRespuesta == null){
            throw new RespuestaInvalidaError();
        }
        return (this.jugador == unaRespuesta.obtenerJugador());
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if (!jugada.obtenerPregunta().conPenalidad()){
            throw new AplicacionDeComodinInvalidaError();
        }
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError, RespuestaError {
        if((!this.esValido(unaRespuesta)) || (!this.esValido(otraRespuesta))){
            throw new AplicacionDeComodinInvalidaError();
        }
        unaRespuesta.aplicarComodin(this);
        otraRespuesta.aplicarComodin(this);
    }
}
