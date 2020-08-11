package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;

public class PuntoExacto extends Punto {

    private Integer valor = 0;

    @Override
    public Punto obtenerPunto() {
        return this;
    }

    @Override
    public Integer obtenerValor(){
        return this.valor;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoExacto clone() {
        PuntoExacto clonado = new PuntoExacto();
        try {
            clonado.agregarValor(this);
        } catch (PuntoError puntoError) {
            puntoError.printStackTrace();
        }
        return clonado;
    }

    public void agregarValor(Punto punto) throws PuntoError {
        try{this.valor += punto.obtenerValor();
        } catch (PuntoError excepcion){
                this.agregarValor(punto.obtenerPunto());
        }
    }
}
