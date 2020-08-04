package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
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
        jugada.agregarComodin(this);
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError, RespuestaError {
        if((!this.esValido(unaRespuesta)) || (!this.esValido(otraRespuesta))){
            throw new ComodinError("Comodin invalido");
        }
        if(this.esAplicable(unaRespuesta,otraRespuesta) || (this.esAplicable(otraRespuesta,unaRespuesta))) {
            unaRespuesta.aplicarComodin(this);
            otraRespuesta.aplicarComodin(this);
        }
        /*if((unaRespuesta.obtenerJugador().obtenerComodines().contains(this)) &&
        (otraRespuesta.obtenerJugador().obtenerComodines().contains(this))){

            unaRespuesta.obtenerJugador().obtenerComodines().remove(this);
            otraRespuesta.obtenerJugador().obtenerComodines().contains(this);
         }
        */
    }

    protected boolean esAplicable(Respuesta unaRespuesta, Respuesta otraRespuesta) {
        return ((unaRespuesta.esCorrecta()) && (!otraRespuesta.esCorrecta()));
    }

}
