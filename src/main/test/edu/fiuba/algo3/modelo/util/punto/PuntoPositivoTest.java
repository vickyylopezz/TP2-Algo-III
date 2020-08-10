package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoPositivoTest {

    // obtenerValor
    @Test
    public void obtenerValorDevuelveUno(){
        PuntoPositivo punto = new PuntoPositivo();

        assertEquals(1, punto.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor() throws PuntoError {
        PuntoPositivo punto = new PuntoPositivo();

        Punto copia = punto.copiar();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntoNegativoConElMismoValor() {
        PuntoPositivo punto = new PuntoPositivo();

        PuntoPositivo copia = punto.clone();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConFactorNegativoDevuelvePuntoConValorCero() throws PuntoError {
        PuntoPositivo puntoPositivo = new PuntoPositivo();

        Punto punto = puntoPositivo.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorCeroDevuelvePuntoConValorCero() throws PuntoError {
        PuntoPositivo puntoPositivo = new PuntoPositivo();

        Punto punto = puntoPositivo.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorDosDevuelveUnPuntoConElDobleDelValor() throws PuntoError {
        PuntoPositivo puntoPositivo = new PuntoPositivo();

        Punto punto = puntoPositivo.multiplicarPorFactor(2);

        assertEquals(2, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorGrandeDevuelveLoCorrecto() throws PuntoError {
        PuntoPositivo puntoPositivo = new PuntoPositivo();

        Punto punto = puntoPositivo.multiplicarPorFactor(25);

        assertEquals(25, punto.obtenerPunto().obtenerValor());
    }
}
