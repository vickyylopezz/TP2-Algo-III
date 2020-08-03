package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;

public class Exclusividad extends Comodin {
    private final int factor = 0;

    public Exclusividad(int factor) throws ComodinError {
        super(factor);
    }

    public void asignarA(Jugada jugada) throws ComodinError {
        if(jugada.pregunta().conPenalizacion()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.agregarComodin(this);
    }
}
