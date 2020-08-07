package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;

public class Multiplicador extends Comodin{

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    @Override
    public void validarPregunta(Jugada jugada) throws ComodinError {
        if (!jugada.obtenerPregunta().conPenalidad()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
    }

    @Override
    public void aplicarARespuestas(ArrayList<Respuesta> respuestas) {
        for(Respuesta respuesta : respuestas) respuesta.aplicarComodin(this);
    }
}
