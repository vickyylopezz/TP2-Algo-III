package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

//Componente
public abstract class Punto {
    abstract public Punto obtenerPunto() throws PuntoError;

    abstract public Integer obtenerValor() throws PuntoError;

    abstract public Punto copiar();

    public Punto multiplicarPorFactor(Integer factor) {
        Puntaje puntaje = new Puntaje();
        for (int i = 0; i < factor; i++) puntaje.agregarPunto(this.copiar());
        return puntaje;
    }


}

