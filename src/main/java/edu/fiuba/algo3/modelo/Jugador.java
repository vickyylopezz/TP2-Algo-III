package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Composite.Puntaje;
import edu.fiuba.algo3.Composite.Punto;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Puntaje puntaje;
    private ArrayList<Respuesta> respuestas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
        this.puntaje = new Puntaje();
    }

    public String nombre() {
        return this.nombre;
    }

    public ArrayList<Respuesta> obtenerRespuestas() {
        return this.respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta) throws JugadorError {
        if (this.respuestas.contains(respuesta)) {
            throw new JugadorError(respuesta.toString() + " ya se encuentra en las respuestas");
        }
        this.respuestas.add(respuesta);
    }

    public void sumarPuntaje(Punto punto) {
        this.puntaje.agregarPunto(punto);
    }

    public Punto puntajeTotal() {
        return this.puntaje;
    }
}
