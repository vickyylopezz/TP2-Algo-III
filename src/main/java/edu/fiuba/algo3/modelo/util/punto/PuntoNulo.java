package edu.fiuba.algo3.modelo.util.punto;

//Hoja
public class PuntoNulo extends Punto {

    @Override
    public Integer obtenerValor(){
        return 0;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoNulo clone() { return new PuntoNulo(); }
}
