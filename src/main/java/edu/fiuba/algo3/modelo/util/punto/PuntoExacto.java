package edu.fiuba.algo3.modelo.util.punto;

public class PuntoExacto extends Punto {

    public PuntoExacto() {
        this.valor = 0;
    }

    public void agregarValor(Punto punto){
        this.valor += punto.obtenerValor();
    }

    public Integer obtenerValor() {
        return this.valor;
    }

    @Override
    public Punto copiar() { return this.clone(); }


    @Override
    public PuntoExacto clone() {
        PuntoExacto clonado = new PuntoExacto();
        clonado.agregarValor(this);
        return clonado;
    }
}
