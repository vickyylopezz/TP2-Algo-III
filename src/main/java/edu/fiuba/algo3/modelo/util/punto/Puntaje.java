package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeNoTieneValorError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

import java.util.ArrayList;

//Compuesto
public class Puntaje extends Punto {

    private final ArrayList<Punto> puntos;

    public Puntaje() {
        this.puntos = new ArrayList<>();
    }

    public void agregarPunto(Punto punto){
        if (punto == null) return;
        this.puntos.add(punto);
    }

    public Punto obtenerPunto() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();
        Integer puntaje = 0;
        for (Punto punto : puntos) puntoExacto.agregarValor(punto);
        return puntoExacto;
    }

    public Integer obtenerValor() throws PuntajeNoTieneValorError {

        throw new PuntajeNoTieneValorError();
    }

    @Override
    public Punto copiar() { return this.clone(); }


    @Override
    public Puntaje clone() {
        Puntaje clonado = new Puntaje();
        for (Punto punto: this.puntos) clonado.agregarPunto(punto);
        return clonado;
    }
}
