package edu.fiuba.algo3.modelo.preguntas.penalidad;

import edu.fiuba.algo3.modelo.util.punto.Punto;

public interface Penalidad {
    public boolean conPenalidad();
    public Punto puntajeCorrecta();
    public Punto puntajeIncorrecta();
}
