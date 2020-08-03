package edu.fiuba.algo3.modelo.util.punto;
//Hoja
public class PuntoNegativo implements Punto {
    private Integer valor = -1;

    @Override
    public Integer obtenerValor() {
        return this.valor;
    }
}
