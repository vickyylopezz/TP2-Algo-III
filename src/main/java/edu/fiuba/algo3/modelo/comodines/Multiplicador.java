package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Multiplicador extends Comodin{
    private int factor = 0;

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    protected boolean esValido(Respuesta unaRespuesta) throws ComodinError {
        if(unaRespuesta == null){
            throw new ComodinError("Respuesta invalida");
        }
        return (this.jugador == unaRespuesta.obtenerJugador());
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if (!jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
    }

    @Override
    public void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError, RespuestaError {
        if((!this.esValido(unaRespuesta)) || (!this.esValido(otraRespuesta))){
            throw new ComodinError("Comodin invalido");
        }
        unaRespuesta.aplicarComodin(this);
        otraRespuesta.aplicarComodin(this);
    }

    @Override
    public void aplicarARespuestas(ArrayList<Respuesta> respuestas) throws ComodinError, RespuestaError{
        for(Respuesta respuesta : respuestas){
            if(respuesta == null){
                throw new RespuestaError("Respuesta invalida");
            }
            if(!this.esValido(respuesta)){
                throw new ComodinError("Comodin invalido");
            }
            respuesta.aplicarComodin(this);
        }
    }
}
