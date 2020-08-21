package edu.fiuba.algo3.modelo.util.punto;

public class PuntoNulo extends Punto {

    @Override
    public Integer obtenerValor(){
        return 0;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoNulo clone() {
        try { return (PuntoNulo) super.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }
}
