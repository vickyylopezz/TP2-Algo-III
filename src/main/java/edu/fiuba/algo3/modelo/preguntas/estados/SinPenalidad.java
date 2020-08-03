package edu.fiuba.algo3.modelo.preguntas.estados;

import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;

public class SinPenalidad extends EstadoPregunta {

    protected SinPenalidad(CalculadorPuntaje calculadorPuntaje) {
        super(calculadorPuntaje);
    }

    @Override
    public Boolean conPenalidad() {
        return false;
    }

    @Override
    public Punto puntajeIncorrecto() {
        return new PuntoNulo();
    }
}
