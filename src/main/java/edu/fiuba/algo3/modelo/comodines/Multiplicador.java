package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Multiplicador extends Comodin{
    private int factor = 0;

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    boolean esValido(Respuesta unaRespuesta) throws ComodinError {
        if(unaRespuesta == null){
            throw new ComodinError("Respuesta invalida");
        }
        return (this.jugador == unaRespuesta.obtenerJugador());
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError, JugadorError {
        if (!jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.seleccionarComodin(this);
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError, RespuestaError {
        if((!this.esValido(unaRespuesta)) || (!this.esValido(otraRespuesta))){
            throw new ComodinError("Comodin invalido");
        }
        unaRespuesta.aplicarComodin(this);
        otraRespuesta.aplicarComodin(this);
    }
}
