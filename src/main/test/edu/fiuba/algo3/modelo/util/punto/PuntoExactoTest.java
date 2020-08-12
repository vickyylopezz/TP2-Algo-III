package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoExactoTest {
    // obtenerValor
    @Test
    public void obtenerValorDevuelveCero() {
        PuntoExacto punto = new PuntoExacto();

        assertEquals(0, punto.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor() throws PuntoError {
        PuntoExacto punto = new PuntoExacto();

        Punto copia = punto.copiar();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntoNegativoConElMismoValor() {
        PuntoExacto punto = new PuntoExacto();

        PuntoExacto copia = punto.clone();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConFactorNegativoDevuelvePuntoConValorCero() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();

        Punto punto = puntoExacto.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorCeroDevuelvePuntoConValorCero() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();

        Punto punto = puntoExacto.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorDosDevuelveUnPuntoConElDobleDelValor() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();

        puntoExacto.agregarValor(new PuntoPositivo());
        Punto punto = puntoExacto.multiplicarPorFactor(2);

        assertEquals(2, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorGrandeDevuelveLoCorrecto() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();
        puntoExacto.agregarValor(new PuntoNegativo());
        Punto punto = puntoExacto.multiplicarPorFactor(25);

        assertEquals(-25, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorConDosValoresAgregadosFactorConFactorGrandeDevuelveLoCorrecto() throws PuntoError {
        PuntoExacto puntoExacto = new PuntoExacto();

        puntoExacto.agregarValor(new PuntoPositivo());
        puntoExacto.agregarValor(new PuntoPositivo());
        Punto punto = puntoExacto.multiplicarPorFactor(10);

        assertEquals(20, punto.obtenerPunto().obtenerValor());
    }
}
