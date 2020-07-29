package edu.fiuba.algo3.Composite;

import java.util.ArrayList;

//Compuesto
public class Puntaje implements Punto {
    private ArrayList<Punto> puntos = new ArrayList<Punto>();

    public Puntaje() {
        puntos.add(new PuntoNulo());
    }

    public void agregarPunto(Punto punto){
        this.puntos.add(punto);
    }

    @Override
    public Integer getValor() {
        Integer puntaje = 0;
        for (Punto punto : puntos){
            puntaje += punto.getValor();
        }
        return puntaje;
    }


}
