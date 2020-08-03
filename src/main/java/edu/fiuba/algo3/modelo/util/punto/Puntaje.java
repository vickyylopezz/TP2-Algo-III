package edu.fiuba.algo3.modelo.util.punto;

import java.util.ArrayList;

//Compuesto
public class Puntaje implements Punto {
    private ArrayList<Punto> puntos;

    public Puntaje() {
        this.puntos = new ArrayList<>();
    }

    public void agregarPunto(Punto punto){
        this.puntos.add(punto);
    }

    @Override
    public Integer obtenerValor() {
        Integer puntaje = 0;
        for (Punto punto : puntos) puntaje += punto.obtenerValor();
        return puntaje;
    }
}
