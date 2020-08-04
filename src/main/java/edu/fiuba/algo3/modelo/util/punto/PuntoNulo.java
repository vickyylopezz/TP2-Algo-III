package edu.fiuba.algo3.modelo.util.punto;
//Hoja
public class PuntoNulo implements Punto {
    private Integer valor = 0;

    @Override
    public Integer obtenerValor() {
        return this.valor;
    }

    public Punto modificarValor(int factor){
        this.valor *= factor;
        return this;
    }
}
