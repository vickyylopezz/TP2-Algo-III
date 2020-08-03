package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoNuloTest {
    @Test
    public void obtenerValorDevuelveCero() {
        PuntoNulo punto = new PuntoNulo();

        assertEquals(0, punto.obtenerValor());
    }
}
