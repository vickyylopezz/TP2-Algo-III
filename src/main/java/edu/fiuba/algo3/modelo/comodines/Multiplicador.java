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
        if (!jugada.obtenerPregunta().conPenalizacion()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.agregarComodin(this);
    }

    @Override
    void aplicarARespuestas(Respuesta unaRespuesta, Respuesta otraRespuesta) throws RespuestaError {
        if(this.esAplicable(unaRespuesta)){
            unaRespuesta.agregarComodin(this);
        }
        if(this.esAplicable(otraRespuesta)){
            otraRespuesta.agregarComodin(this);
        }
    }

    boolean esAplicable(Respuesta unaRespuesta)throws RespuestaError {
        return (this.jugador.obtenerComodinesDeRespuesta(unaRespuesta).contains(this));
    }

}
