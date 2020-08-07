package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntajeTest {

    // obtenerValor
    @Test
    public void obtenerValorSinAgregarPuntoEsCero() {
        Puntaje puntaje = new Puntaje();

        assertEquals(0, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoPuntoPositivoValorDevuelveUno() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoVariosPuntosLosSuma() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        assertEquals(-1, puntaje.obtenerValor());
    }
}
