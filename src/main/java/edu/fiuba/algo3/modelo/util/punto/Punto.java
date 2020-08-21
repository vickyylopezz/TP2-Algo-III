package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeIgualError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

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

    public boolean mayor(Punto puntajeTotal) throws PuntoError {
        if(this.obtenerValor() > puntajeTotal.obtenerValor()){
            return true;
        }else if(this.obtenerValor() < puntajeTotal.obtenerValor()){
            return false;
        }else{
            throw new PuntajeIgualError();
        }
    }
}

