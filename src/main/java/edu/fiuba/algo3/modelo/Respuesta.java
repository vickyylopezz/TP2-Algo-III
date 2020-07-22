package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Respuesta {
    protected Pregunta pregunta;
    protected Jugador jugador;
    protected ArrayList<Opcion> opcionesElegidas;

    public Respuesta(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
    }

    public void agregarOpcion(Opcion opcion) {
        this.opcionesElegidas.add(opcion);
    }

    public Integer puntaje() {
        Integer puntaje = 0;
        for(int i = 0; i < opcionesElegidas.size(); i++){
            puntaje += opcionesElegidas.get(i).getValor();
        }
        return puntaje;
    }
}
