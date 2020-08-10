package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeNoTieneValorError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntajeTest {

    // obtenerValor
    @Test
    public void obtenerValorSinAgregarPuntoEsCero() throws PuntoError {
        Puntaje puntaje = new Puntaje();

        assertEquals(0, puntaje.obtenerPunto().obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoPuntoPositivoValorDevuelveUno() throws PuntoError {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerPunto().obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoVariosPuntosLosSuma() throws PuntoError {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        assertEquals(-1, puntaje.obtenerPunto().obtenerValor());
    }

    // agregarPunto
    @Test
    public void agregarPuntoNuloNoHaceNada() throws PuntoError {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(null);

        assertEquals(0, puntaje.obtenerPunto().obtenerValor());
    }

    @Test
    public void agregarPuntoPositivoAumentaElValor() throws PuntoError {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerPunto().obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto copia = puntaje.copiar();

        assertEquals(puntaje.obtenerPunto().obtenerValor(), copia.obtenerPunto().obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntajeConElMismoValor() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Puntaje copia = puntaje.clone();

        assertEquals(puntaje.obtenerPunto().obtenerValor(), copia.obtenerPunto().obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorNegativoDevuelvePuntoConValorCero() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorCeroDevuelvePuntoConValorCero() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorDosDevuelveUnPuntoConElDobleDelValor() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(2);

        assertEquals(4, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorGrandeDevuelveLoCorrecto() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(25);

        assertEquals(50, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorNegativoDevuelvePuntoConValorCero() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorCeroDevuelvePuntoConValorCero() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorDosDevuelveUnPuntoConElDobleDelValor() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(3);

        assertEquals(-9, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorGrandeDevuelveLoCorrecto() throws PuntoError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(30);

        assertEquals(-60, punto.obtenerPunto().obtenerValor());
    }
}
