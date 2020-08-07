package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.comodines.Exclusividad;
import edu.fiuba.algo3.modelo.comodines.Multiplicador;
import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private Puntaje puntaje;
    private ArrayList<Respuesta> respuestas;
    private ArrayList<Comodin> comodines;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
        this.comodines = new ArrayList<>();
        this.puntaje = new Puntaje();
    }

    public String nombre() {
        return this.nombre;
    }

    public ArrayList<Respuesta> obtenerRespuestas() { return new ArrayList<>(this.respuestas); }

    public ArrayList<Comodin> obtenerComodines() { return new ArrayList<>(this.comodines); }

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

    public void agregarComodin(Comodin comodin) throws JugadorError {
        if (this.comodines.contains(comodin)) {
            throw new JugadorError(comodin.toString() + " ya se encuentra en " + this.toString());
        }
        this.comodines.add(comodin);
    }

    public void sacarComodin(Comodin comodin) throws JugadorError {
        if (!this.comodines.contains(comodin)) {
            throw new JugadorError(comodin.toString() + " no se encuentra en " + this.toString());
        }
        this.comodines.remove(comodin);
    }

    public Punto puntajeTotal() { return this.puntaje; }

    public void validarComodin(Comodin comodin) throws JugadorError {
        if (!this.comodines.contains(comodin)) {
            throw new JugadorError(comodin.toString() + " no esta en el jugador");
        }
    }
}
