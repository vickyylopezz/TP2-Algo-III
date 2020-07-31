package edu.fiuba.algo3.modelo.util.tiempo;

import java.util.Date;

public class Segundo {

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
}
