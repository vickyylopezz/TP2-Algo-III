package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private Integer puntajeTotal;
    private ArrayList<Respuesta> respuestas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
        this.puntajeTotal = 0;
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

    public void sumarPuntaje(Integer puntaje) {

        this.puntajeTotal = this.puntajeTotal + puntaje;
    }

    public Integer puntajeTotal() {
        return this.puntajeTotal;
    }
}
