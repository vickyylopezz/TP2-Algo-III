package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

public class SinPenalidad implements Penalidad {

    @Override
    public boolean conPenalidad() {
        return false;
    }

    @Override
    public Punto puntajeOpcionCorrecta() {
        return new PuntoPositivo();
    }

    @Override
    public Punto puntajeOpcionIncorrecta() {
        return new PuntoNulo();
    }
}
