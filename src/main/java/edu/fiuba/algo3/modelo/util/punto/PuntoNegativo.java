package edu.fiuba.algo3.modelo.util.punto;

public class PuntoNegativo extends Punto {

    @Override
    public Integer obtenerValor() { return -1; }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoNegativo clone() {
        try { return (PuntoNegativo) super.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }
}
