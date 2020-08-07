package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import java.util.ArrayList;

public class Respuesta {

    private final Pregunta pregunta;
    private final Jugador jugador;
    private final ArrayList<Opcion> opciones;
    private final ArrayList<Comodin> comodines;
    
    public Respuesta(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
        this.opciones = new ArrayList<>();
        this.comodines = new ArrayList<>();
    }

    public Jugador obtenerJugador() {
        return this.jugador;
    }

    public Pregunta obtenerPregunta() {
        return this.pregunta;
    }

    public ArrayList<Opcion> opcionesElegidas() {
        return new ArrayList<>(this.opciones);
    }

    public ArrayList<Comodin> comodinesAplicados() {
        return new ArrayList<>(this.comodines);
    }

    public void agregarOpcion(Opcion opcion) {
        if (opcion == null || this.opciones.contains(opcion)) return;
        this.opciones.add(opcion);
    }

    public void sacarOpcion(Opcion opcion) { this.opciones.remove(opcion); }

    public void aplicarComodin(Comodin comodin) {
        if (comodin == null || this.comodines.contains(comodin)) return;
        this.comodines.add(comodin);
    }

    public boolean esCorrecta() {
        return this.pregunta.opcionesCorrectas(this.opcionesElegidas());
    }
}
