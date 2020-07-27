package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Respuesta {
    protected Pregunta pregunta;
    protected Jugador jugador;
    protected ArrayList<Opcion> opcionesElegidas = new ArrayList<>();

    public Respuesta(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
    }

    public void agregarOpcion(Opcion opcion) throws RespuestaError {
        if (this.opcionesElegidas.contains(opcion)){
            throw new RespuestaError(opcion.toString() + "ya fue elegida");
        }
        this.opcionesElegidas.add(opcion);
    }

    public ArrayList<Opcion> obtenerOpcionesElegidas() {
        return new ArrayList<Opcion>(this.opcionesElegidas);
    }

    public Integer obtenerPuntaje(){
        return this.pregunta.puntajeConOpciones(this.opcionesElegidas);
    }
}
