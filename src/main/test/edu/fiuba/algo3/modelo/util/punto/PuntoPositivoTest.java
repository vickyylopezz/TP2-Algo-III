package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoPositivoTest {
    @Test
    public void obtenerValorDevuelveUno() {
        PuntoPositivo punto = new PuntoPositivo();

        assertEquals(1, punto.obtenerValor());
    }
}
