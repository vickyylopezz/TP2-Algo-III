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

    @Test
    public void sumarSEEntreDosSegundosDevuelveUnSegundoConLaSuma() {
        Segundo seg1 = Segundo.crearConLiteral(5L);
        Segundo seg2 = Segundo.crearConLiteral(40L);

        Segundo segRes = seg1.sumarSE(seg2);

        assertEquals(45, segRes.valor());
    }

    @Test
    public void restarSEEntreDosSegundosDevuelveUnSegundoConLaResta() {
        Segundo seg1 = Segundo.crearConLiteral(5L);
        Segundo seg2 = Segundo.crearConLiteral(40L);

        Segundo segRes = seg1.restarSE(seg2);

        assertEquals(-35, segRes.valor());
    }

    @Test
    public void sumarConUnTiempoDevuelveUnTiempo() {
        Segundo seg1 = Segundo.crearConLiteral(50L);
        Tiempo tiempo = Segundo.crearConLiteral(-20L);

        Tiempo resultado = seg1.sumar(tiempo);
        Segundo resultadoSeg = resultado.segundos();

        assertEquals(30L, resultadoSeg.valor());
    }

    @Test
    public void restarConUnTiempoDevuelveUnTiempo() {
        Segundo seg1 = Segundo.crearConLiteral(50L);
        Tiempo tiempo = Segundo.crearConLiteral(-20L);

        Tiempo resultado = seg1.restar(tiempo);
        Segundo resultadoSeg = resultado.segundos();

        assertEquals(70L, resultadoSeg.valor());
    }

    @Test
    public void milisegundosSeDevuelveLosSegundosEnMiliSegundos() {
        Segundo seg = Segundo.crearConLiteral(5L);
        Tiempo tiempo = seg;

        MiliSegundo res = tiempo.miliSegundos();

        assertEquals(5000, res.valor());
    }

    @Test
    public void segundosSeDevuelveASiMismo() {
        Segundo seg = Segundo.crearConLiteral(5L);
        Tiempo tiempo = seg;

        Segundo res = tiempo.segundos();

        assertEquals(5, res.valor());
    }
}
