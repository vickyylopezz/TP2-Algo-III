package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.comodines.Exclusividad;
import edu.fiuba.algo3.modelo.comodines.Multiplicador;
import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private String nombre;
    private Puntaje puntaje;
    private ArrayList<Respuesta> respuestas;
    private ArrayList<Comodin> comodines;

    public Jugador(String nombre) throws ComodinError {
        this.nombre = nombre;
        this.respuestas = new ArrayList<>();
        this.puntaje = new Puntaje();
        this.comodines = new ArrayList<>();
        inicializarComodines(this.comodines);
    }

    public void inicializarComodines(ArrayList<Comodin> comodines) throws ComodinError {
        comodines.add(new Multiplicador(2));
        comodines.add(new Multiplicador(3));
        comodines.add(new Exclusividad(2));
        comodines.add(new Exclusividad(2));
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

    public ArrayList<Comodin> obtenerComodinesDeRespuesta(Respuesta respuesta) throws RespuestaError {
        if(!this.respuestas.contains(respuesta)){
            throw new RespuestaError(respuesta.toString() + "no se encuentra en lista de respuestas");
        }
        int index = this.respuestas.indexOf(respuesta);
        return this.respuestas.get(index).obtenerComodines();
    }

    public ArrayList<Comodin> obtenerComodines() {
        return this.comodines;
    }
}
