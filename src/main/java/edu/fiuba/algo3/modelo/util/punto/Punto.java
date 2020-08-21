package edu.fiuba.algo3.modelo.util.punto;

public abstract class Punto implements Cloneable {
    protected Integer valor;

    abstract public Integer obtenerValor();

    abstract public Punto copiar();

    public Punto multiplicarPorFactor(Integer factor) {
        PuntoExacto puntaje = new PuntoExacto();
        for (int i = 0; i < factor; i++) puntaje.agregarValor(this.copiar());
        return puntaje;
    }
}

