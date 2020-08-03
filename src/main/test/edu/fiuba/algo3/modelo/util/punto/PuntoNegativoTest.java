package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoNegativoTest {
    @Test
    public void obtenerValorDevuelveMenosUno() {
        PuntoNegativo punto = new PuntoNegativo();

        assertEquals(-1, punto.obtenerValor());
    }
}
