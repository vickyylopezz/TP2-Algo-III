package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

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

    public ArrayList<Respuesta> obtenerRespuestas() { return new ArrayList<>(this.respuestas); }

    public void agregarRespuesta(Respuesta respuesta) throws JugadorError {
        if (this.respuestas.contains(respuesta)) {
            throw new JugadorError(respuesta.toString() + " ya se encuentra en las respuestas");
        }
        this.respuestas.add(respuesta);
    }

    public void sacarRespuesta(Respuesta respuesta) throws JugadorError {
        if (!this.respuestas.contains(respuesta)) {
            throw new JugadorError(respuesta.toString() + " no se encuentra en las respuestas");
        }
        this.respuestas.remove(respuesta);
    }

    public void sumarPuntaje(Punto punto) {
        this.puntaje.agregarPunto(punto);
    }

    public Punto puntajeTotal() {
        return this.puntaje;
    }
}
