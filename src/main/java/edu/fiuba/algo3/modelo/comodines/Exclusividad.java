package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Exclusividad extends Comodin {
    private final int factor = 0;

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
        for(Respuesta respuesta : respuestas){
            if(respuesta == null){
                throw new RespuestaError("Respuesta invalida");
            }
            if(this.esAplicable(respuestas)){
                if(respuesta.validarComodin(this)){
                    respuesta.aplicarComodin(this);
                    respuesta.eliminarComodin(this);
                }
            }
        }
    }

    protected boolean esAplicable(Respuesta unaRespuesta, Respuesta otraRespuesta) {
        return ((unaRespuesta.esCorrecta()) && (!otraRespuesta.esCorrecta()));
    }

    protected boolean esAplicable(ArrayList<Respuesta> respuestas) {
        int respuestasCorrectas = 0;
        for(Respuesta respuesta : respuestas){
            if(respuesta.esCorrecta()){
                respuestasCorrectas++;
            }
        }
        if(respuestasCorrectas == 0 || respuestasCorrectas > 1){
            return false;
        }
        return true;
    }
}
