package edu.fiuba.algo3.modelo.util.punto;

//Componente
public abstract class Punto {
    protected Integer valor;

    abstract public Integer obtenerValor();

    abstract public Punto copiar();

    public Punto multiplicarPorFactor(Integer factor) {
        PuntoExacto puntaje = new PuntoExacto();
        for (int i = 0; i < factor; i++) puntaje.agregarValor(this.copiar());
        return puntaje;
    }


}

