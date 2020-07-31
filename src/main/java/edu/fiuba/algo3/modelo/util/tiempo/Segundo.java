package edu.fiuba.algo3.modelo.util.tiempo;

import java.util.Date;

public class Segundo implements Tiempo {

    private final MiliSegundo ms;

    public Segundo(MiliSegundo ms) {
        this.ms = ms;
    }

    public static Segundo crearConLiteral(Long valor) {
        MiliSegundo ms = new MiliSegundo(valor * 1000L);
        return new Segundo(ms);
    }

    public static Segundo crearConFecha(Date fecha) {
        MiliSegundo ms = MiliSegundo.crearConFecha(fecha);
        return new Segundo(ms);
    }

    public float valor() {
        return this.ms.valor() / 1000;
    }

    public Segundo sumarSE(Segundo otroSegundo) {
        MiliSegundo ms = this.ms.sumarMS(otroSegundo.ms);
        return new Segundo(ms);
    }

    public Segundo restarSE(Segundo otroSegundo) {
        MiliSegundo ms = this.ms.restarMS(otroSegundo.ms);
        return new Segundo(ms);
    }

    @Override
    public Tiempo sumar(Tiempo otroTiempo) {
        MiliSegundo ms = this.ms.sumarMS(otroTiempo.miliSegundos());
        return new Segundo(ms);
    }

    @Override
    public Tiempo restar(Tiempo otroTiempo) {
        MiliSegundo ms = this.ms.restarMS(otroTiempo.miliSegundos());
        return new Segundo(ms);
    }

    @Override
    public Segundo segundos() {
        return this;
    }

    @Override
    public MiliSegundo miliSegundos() {
        return new MiliSegundo(this.ms.valor());
    }
}
