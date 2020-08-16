package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeIgualError;
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


    public boolean mayor(Punto puntajeTotal) throws PuntoError {
        if(this.obtenerPunto().obtenerValor() > puntajeTotal.obtenerPunto().obtenerValor()){
            return true;
        }else if(this.obtenerPunto().obtenerValor() < puntajeTotal.obtenerPunto().obtenerValor()){
            return false;
        }else{
            throw new PuntajeIgualError();
        }
    }
}

