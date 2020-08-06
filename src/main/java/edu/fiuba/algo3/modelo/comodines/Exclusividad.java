package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

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
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws RespuestaError, JugadorError {
        if(this.esAplicable(unaRespuesta,otraRespuesta) || (this.esAplicable(otraRespuesta,unaRespuesta))) {
            unaRespuesta.aplicarComodin(this);
            otraRespuesta.aplicarComodin(this);
        }
        if(unaRespuesta.validarComodin(this) && otraRespuesta.validarComodin(this)){
            unaRespuesta.eliminarComodin(this);
            otraRespuesta.eliminarComodin(this);
         }

    }
    protected boolean esAplicable(Respuesta unaRespuesta, Respuesta otraRespuesta) {
        return ((unaRespuesta.esCorrecta()) && (!otraRespuesta.esCorrecta()));
    }

}
