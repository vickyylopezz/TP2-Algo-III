package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Respuesta> respuestas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
    }

    public String Nombre() {
        return this.nombre;
    }

    public ArrayList<Respuesta> ObtenerRespuestas() {
        return this.respuestas;
    }

    public void AgregarRespuesta(Respuesta respuesta) throws JugadorError {
        if (this.respuestas.contains(respuesta)) {
            throw new JugadorError(respuesta.toString() + " ya se encuentra en las respuestas");
        }
        this.respuestas.add(respuesta);
    }

    public Integer PuntajeTotal() {
        Integer resultado = 0;
        for (Respuesta respuesta : this.respuestas) resultado += respuesta.puntaje();
        return resultado;
    }
}
