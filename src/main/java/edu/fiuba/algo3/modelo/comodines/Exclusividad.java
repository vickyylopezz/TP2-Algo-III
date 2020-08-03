package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Exclusividad extends Comodin {
    private final int factor = 0;

    public Exclusividad(int factor) throws ComodinError {
        super(factor);
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if(jugada.obtenerPregunta().conPenalizacion()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.agregarComodin(this);
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws ComodinError {
        if(!this.esAplicable(unaRespuesta,otraRespuesta) || (!this.esAplicable(otraRespuesta,unaRespuesta))) {
            return;
        }
        unaRespuesta.agregarComodin(this);
        otraRespuesta.agregarComodin(this);
    }

    protected boolean esAplicable(Respuesta unaRespuesta, Respuesta otraRespuesta) {
        return ((unaRespuesta.esCorrecta()) && (!otraRespuesta.esCorrecta()));
    }

}
