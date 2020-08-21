package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.comodin.AplicacionDeComodinInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Multiplicador extends Comodin{

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    @Override
    public void validarPregunta(Pregunta pregunta) throws ComodinError {
        if (!pregunta.conPenalidad()){
            throw new AplicacionDeComodinInvalidaError();
        }
    }

    @Override
    public void aplicarARespuestas(ArrayList<Respuesta> respuestas) {
        for(Respuesta respuesta : respuestas)
            if(respuesta.obtenerJugador() == this.jugador){
                respuesta.aplicarComodin(this);
            }
    }

    @Override
    public String toString() { return "x" + this.factor; }
}
