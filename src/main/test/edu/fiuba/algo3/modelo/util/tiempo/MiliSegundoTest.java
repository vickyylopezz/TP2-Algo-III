package edu.fiuba.algo3.modelo.util.tiempo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiliSegundoTest {

    // Test unitarios

    // contructor
    @Test
    public void metodoEstaticoCrearConLiteralDevuelveMiliSegundos() {
        assertDoesNotThrow(() -> MiliSegundo.crearConLiteral(1000L));
    }

    @Test
    public void metodoEstaticoCrearConFechaGuardaTiempoUnix() {
        assertDoesNotThrow(() -> MiliSegundo.crearConFecha(new Date()));
    }

    @Test
    public void valorDevuelveElValorEnLongDeLosMilisegundosLiteral() {
        long valor = 1000L;
        MiliSegundo ms = MiliSegundo.crearConLiteral(valor);
        assertEquals(valor, ms.valor());
    }

    @Test
    public void valorDevuelveElValorEnLongDeLosMilisegundosFecha() {
        Date fecha = new Date();
        MiliSegundo ms = MiliSegundo.crearConFecha(fecha);
        assertEquals(fecha.getTime(), ms.valor());
    }
}
