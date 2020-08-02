package edu.fiuba.algo3.modelo.preguntas.penalidad;

import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

public class ConPenalidad implements Penalidad {

    @Override
    public boolean conPenalidad() {
        return true;
    }

    @Override
    public Punto puntajeCorrecta() {
        return new PuntoPositivo();
    }

    @Override
    public Punto puntajeIncorrecta() {
        return new PuntoNegativo();
    }
}
