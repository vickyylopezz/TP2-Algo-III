package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Exclusividad extends Comodin {

    public Exclusividad(int factor) throws ComodinError {
        super(factor);
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if(jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
    }

    @Override
    public void validarJugada(Jugada jugada) throws ComodinError {
        if(jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
    }

    @Override
    public void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws RespuestaError, JugadorError {
        if(unaRespuesta.validarComodin(this) && otraRespuesta.validarComodin(this)) {
            if(this.esAplicable(unaRespuesta,otraRespuesta) || (this.esAplicable(otraRespuesta,unaRespuesta))) {
                unaRespuesta.aplicarComodin(this);
                otraRespuesta.aplicarComodin(this);
                unaRespuesta.eliminarComodin(this);
                otraRespuesta.eliminarComodin(this);
            }
        }
    }

    @Override
    public void aplicarARespuestas(ArrayList<Respuesta> respuestas) throws RespuestaError, JugadorError {
        Respuesta respuestaCorrecta = null;
        for(Respuesta respuesta : respuestas){
            if(!respuesta.esCorrecta()) continue;
            if(respuestaCorrecta != null) return;
            respuestaCorrecta = respuesta;
            if(respuesta.validarComodin(this)){
                    respuesta.aplicarComodin(this);
                    respuesta.eliminarComodin(this);
                }
            }
    }

    protected boolean esAplicable(Respuesta unaRespuesta, Respuesta otraRespuesta) {
        return ((unaRespuesta.esCorrecta()) && (!otraRespuesta.esCorrecta()));
    }
}
