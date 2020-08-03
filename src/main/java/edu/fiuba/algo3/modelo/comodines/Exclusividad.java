package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Exclusividad extends Comodin {
    private final int factor = 0;

    public Exclusividad(int factor) throws ComodinError {
        super(factor);
    }

    public void asignarA(Respuesta respuesta) throws ComodinError {
        if(respuesta.pregunta().conPenalizacion()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        respuesta.agregarComodin(this);
    }
}
