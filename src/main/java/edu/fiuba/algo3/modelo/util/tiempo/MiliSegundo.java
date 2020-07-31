package edu.fiuba.algo3.modelo.util.tiempo;

import java.util.Date;

public class MiliSegundo implements Tiempo {

    private final Long valor;

    private MiliSegundo(Long valor) { this.valor = valor; }

    public static MiliSegundo crearConLiteral(long valor) {
        return new MiliSegundo(valor);
    }

    public static MiliSegundo crearConFecha(Date fecha) {
        return new MiliSegundo(fecha.getTime());
    }

    public Long valor() { return this.valor; }

    public MiliSegundo sumarMS(MiliSegundo otroMilisegundo) {
        Long valor = this.valor + otroMilisegundo.valor;
        return new MiliSegundo(valor);
    }

    public MiliSegundo restarMS(MiliSegundo otroMilisegundo) {
        Long valor = this.valor - otroMilisegundo.valor;
        return new MiliSegundo(valor);
    }

    @Override
    public Tiempo sumar(Tiempo otroTiempo) {
        return this.sumarMS(otroTiempo.miliSegundos());
    }

    @Override
    public Tiempo restar(Tiempo otroTiempo) {
        return this.restarMS(otroTiempo.miliSegundos());
    }

    @Override
    public Segundo segundos() {
        // return Segundo.crearConMS(this);
        return null;
    }

    @Override
    public MiliSegundo miliSegundos() { return this; }
}
