package edu.fiuba.algo3.modelo.util.tiempo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegundoTest {

    // Test unitarios

    // contructor
    @Test
    public void contructurSeCreaConMilisegundos() {
        MiliSegundo ms = new MiliSegundo(400L);
        assertDoesNotThrow(() -> new Segundo(ms));
    }

    @Test
    public void metodoEstaticoCrearConLiteralDevuelveSegundos() {
        assertDoesNotThrow(() -> Segundo.crearConLiteral(10L));
    }

    @Test
    public void metodoEstaticoCrearConFechaGuardaTiempoUnix() {
        assertDoesNotThrow(() -> Segundo.crearConFecha(new Date()));
    }

    @Test
    public void valorDevuelveElValorEnLongDeLosMilisegundos() {
        Segundo seg = new Segundo(new MiliSegundo(1000L));
        assertEquals(1, seg.valor());
    }

    @Test
    public void valorDevuelveElValorEnLongDeLosMilisegundosLiteral() {
        Segundo seg = Segundo.crearConLiteral(5L);
        assertEquals(5, seg.valor());
    }

    @Test
    public void valorDevuelveElValorEnLongDeLosMilisegundosFecha() {
        Date fecha = new Date();
        Segundo seg = Segundo.crearConFecha(fecha);

        assertEquals(fecha.getTime() / 1000, seg.valor());
    }
    /*
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

        //assertEquals(0.5, segundos.valor());
    }
    */
}
