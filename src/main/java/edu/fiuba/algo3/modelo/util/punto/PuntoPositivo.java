package edu.fiuba.algo3.modelo.util.punto;
//Hoja
public class PuntoPositivo implements Punto {
    private Integer valor = 1;

    @Override
    public Integer obtenerValor() {
        return this.valor;
    }
}
