package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.util.punto.Punto;

public interface Penalidad {
    public boolean conPenalidad();
    public Punto puntajeOpcionCorrecta();
    public Punto puntajeOpcionIncorrecta();
}
