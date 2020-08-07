package edu.fiuba.algo3.modelo.util.punto;
//Componente
public abstract class Punto {
    abstract public Integer obtenerValor();

    abstract public Punto copiar();

    public Punto multiplicarPorFactor(Integer factor) {
        Puntaje puntaje = new Puntaje();
        for (int i = 0; i < factor; i++) puntaje.agregarPunto(this.copiar());
        return puntaje;
    }
}
