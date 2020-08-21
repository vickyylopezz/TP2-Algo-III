package edu.fiuba.algo3.modelo.util.punto;

//Hoja
public class PuntoPositivo extends Punto {

    @Override
    public Integer obtenerValor() {
        return 1;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoPositivo clone() { return new PuntoPositivo(); }
}
