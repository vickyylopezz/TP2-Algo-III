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

    @Test
    public void sumarMSEntreDosMilisegundosDevuelveUnMilisegundoConLaSuma() {
        MiliSegundo msMil = MiliSegundo.crearConLiteral(1000L);
        MiliSegundo msCuarenta = MiliSegundo.crearConLiteral(40L);

        MiliSegundo ms = msMil.sumarMS(msCuarenta);

        assertEquals(1040L, ms.valor());
    }

    @Test
    public void restarMSEntreDosMilisegundosDevuelveUnMilisegundoConLaResta() {
        MiliSegundo msQinientos = MiliSegundo.crearConLiteral(500L);
        MiliSegundo msDocientos = MiliSegundo.crearConLiteral(200L);

        MiliSegundo ms = msQinientos.restarMS(msDocientos);

        assertEquals(300L, ms.valor());
    }
}
