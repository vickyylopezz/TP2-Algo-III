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

    @Test
    public void sumarConUnTiempoDevuelveUnTiempo() {
        MiliSegundo msQinientos = MiliSegundo.crearConLiteral(500L);
        Tiempo tiempo = MiliSegundo.crearConLiteral(200L);

        Tiempo resultado = msQinientos.sumar(tiempo);
        MiliSegundo resultadoMs = resultado.miliSegundos();

        assertEquals(700L, resultadoMs.valor());
    }

    @Test
    public void restarConUnTiempoDevuelveUnTiempo() {
        MiliSegundo msQinientos = MiliSegundo.crearConLiteral(500L);
        Tiempo tiempo = MiliSegundo.crearConLiteral(200L);

        Tiempo resultado = msQinientos.restar(tiempo);
        MiliSegundo resultadoMs = resultado.miliSegundos();

        assertEquals(300L, resultadoMs.valor());
    }

    @Test
    public void milisegundosSeDevuelveASiMismo() {
        MiliSegundo msQinientos = MiliSegundo.crearConLiteral(500L);
        Tiempo tiempoMsQuinientos = msQinientos;

        assertEquals(msQinientos, tiempoMsQuinientos.miliSegundos());
    }

    @Test
    public void segundosSeDevuelveSegundosConValorEquvalienteEnSegundos() {
        MiliSegundo msQinientos = MiliSegundo.crearConLiteral(500L);
        Tiempo tiempoMsQuinientos = msQinientos;

        Segundo segundos = tiempoMsQuinientos.segundos();

        assertEquals(0.5, segundos.valor());
    }
}
