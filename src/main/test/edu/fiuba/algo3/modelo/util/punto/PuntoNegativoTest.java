package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeIgualError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuntoNegativoTest {

    // obtenerValor
    @Test
    public void obtenerValorDevuelveMenosUno() {
        PuntoNegativo punto = new PuntoNegativo();

        assertEquals(-1, punto.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor(){
        PuntoNegativo punto = new PuntoNegativo();

        Punto copia = punto.copiar();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntoNegativoConElMismoValor() {
        PuntoNegativo punto = new PuntoNegativo();

        PuntoNegativo copia = punto.clone();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConFactorNegativoDevuelvePuntoConValorCero(){
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        Punto punto = puntoNegativo.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorCeroDevuelvePuntoConValorCero(){
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        Punto punto = puntoNegativo.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorDosDevuelveUnPuntoConElDobleDelValor(){
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        Punto punto = puntoNegativo.multiplicarPorFactor(2);

        assertEquals(-2, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorGrandeDevuelveLoCorrecto(){
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        Punto punto = puntoNegativo.multiplicarPorFactor(25);

        assertEquals(-25, punto.obtenerValor());
    }


    @Test
    public void mayorDevuelveFalseSiElPuntajeEsMenorAlRecibido() throws PuntoError {
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        assertFalse(puntoNegativo.mayor(new PuntoNulo()));
    }

    @Test
    public void mayorLanzaExcepcionSiElPuntajeEsIgualAlRecibido() {
        PuntoNegativo puntoNegativo = new PuntoNegativo();

        assertThrows(PuntajeIgualError.class, ()->puntoNegativo.mayor(new PuntoNegativo()));
    }

}
