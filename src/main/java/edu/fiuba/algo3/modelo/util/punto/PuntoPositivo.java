package edu.fiuba.algo3.modelo.util.punto;

public class PuntoPositivo extends Punto {

    @Override
    public Integer obtenerValor() {
        return 1;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoPositivo clone() {
        try { return (PuntoPositivo) super.clone();
        } catch (CloneNotSupportedException e) { return null; }
    }
}
