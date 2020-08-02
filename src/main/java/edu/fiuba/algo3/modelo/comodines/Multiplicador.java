package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Respuesta;

public class Multiplicador extends Comodin{
    private int factor = 0;

    public Multiplicador(int factor) throws ComodinError {
        super(factor);
    }

    public void asignarA(Jugada jugada) throws ComodinError {
        if(jugada.pregunta().conPenalizacion()){
            throw new ComodinError("Aplicacion de comodin invalida");
        }
        jugada.agregarComodin(this);
    }

}
