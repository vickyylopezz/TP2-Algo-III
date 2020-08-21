package edu.fiuba.algo3.modelo.util.punto;

public class PuntoNulo extends Punto {

    private final Integer valor = 0;

    @Override
    public Integer obtenerValor(){
        return this.valor;
    }

    @Override
    public Punto copiar() { return this.clone(); }

    @Override
    public PuntoNulo clone() { return new PuntoNulo(); }
}
