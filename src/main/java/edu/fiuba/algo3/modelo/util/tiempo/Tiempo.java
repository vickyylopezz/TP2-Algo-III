package edu.fiuba.algo3.modelo.util.tiempo;

public interface Tiempo {
    Tiempo sumar(Tiempo otroTiempo);
    Tiempo restar(Tiempo otroTiempo);
    Segundo segundos();
    MiliSegundo miliSegundos();
}
