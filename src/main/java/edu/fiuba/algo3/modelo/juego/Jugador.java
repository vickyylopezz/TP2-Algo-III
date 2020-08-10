package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorNoTieneAlComodinError;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Respuesta> respuestas;
    private ArrayList<Comodin> comodines;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
        this.comodines = new ArrayList<>();
    }

    public String nombre() {
        return this.nombre;
    }

    public ArrayList<Respuesta> obtenerRespuestas() { return new ArrayList<>(this.respuestas); }

    public ArrayList<Comodin> obtenerComodines() { return new ArrayList<>(this.comodines); }

    public void agregarRespuesta(Respuesta respuesta) {
        if (respuesta == null || this.respuestas.contains(respuesta)) return;
        this.respuestas.add(respuesta);
    }

    public void sacarRespuesta(Respuesta respuesta) {
        this.respuestas.remove(respuesta);
    }

    public void agregarComodin(Comodin comodin) {
        if (comodin == null || this.comodines.contains(comodin)) return;
        this.comodines.add(comodin);
    }

    public void sacarComodin(Comodin comodin) {
        this.comodines.remove(comodin);
    }

    public Punto puntajeTotal() {
        Puntaje puntajeTotal = new Puntaje();
        for (Respuesta respuesta : this.respuestas){
            puntajeTotal.agregarPunto(respuesta.puntaje());
        }
        return puntajeTotal;
    }

    public void validarComodin(Comodin comodin) throws JugadorError {
        if (!this.comodines.contains(comodin)) {
            throw new JugadorNoTieneAlComodinError();
        }
    }
}
