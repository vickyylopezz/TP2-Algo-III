package edu.fiuba.algo3.modelo.preguntas.estados;

import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;

import java.util.ArrayList;

public class ConPenalidad extends EstadoPregunta {

    protected ConPenalidad(CalculadorPuntaje calculadorPuntaje) {
        super(calculadorPuntaje);
    }

    @Override
    public Boolean conPenalidad() { return true; }

    @Override
    public Punto puntajeIncorrecto() { return new PuntoNulo(); }
}
