package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import java.util.ArrayList;

public class Jugada {

    private final Pregunta pregunta;
    private final Jugador jugador;
    private final Respuesta respuesta;
    private Comodin comodin;

    public Jugada(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
        this.respuesta = new Respuesta(pregunta, jugador);
    }

    public String tituloPregunta() { return this.pregunta.obtenerTitulo(); }

    public Pregunta obtenerPregunta() {
        return this.pregunta;
    }

    public Jugador obtenerJugador() {
        return this.jugador;
    }

    public Respuesta obtenerRespuesta() { return this.respuesta; }

    public ArrayList<Opcion> opciones() {
        return this.pregunta.obtenerOpciones();
    }

    public ArrayList<Comodin> comodines() {
        return this.jugador.obtenerComodines();
    }

    public void seleccionarOpcion(Opcion opcion) {
        this.respuesta.agregarOpcion(opcion);
    }

    public void deseleccionarOpcion(Opcion opcion) {
        this.respuesta.sacarOpcion(opcion);
    }

    public void seleccionarComodin(Comodin comodin) throws JugadorError, ComodinError {
        this.jugador.validarComodin(comodin);
        comodin.validarPregunta(this);
        this.comodin = comodin;
    }

    public ArrayList<Opcion> opcionesSeleccionadas() {
        return this.respuesta.opcionesElegidas();
    }

    public Comodin comodinSeleccionado() { return this.comodin; }

    public ArrayList<Opcion> opcionesValidas() {
        ArrayList<Opcion> todasOpciones = this.opciones();
        for (Opcion opcion: this.opcionesSeleccionadas()) {
            todasOpciones.remove(opcion);
        }
        return todasOpciones;
    }

    public ArrayList<Comodin> comodinesValidos() {
        ArrayList<Comodin> todosComodines = this.comodines();
        todosComodines.remove(this.comodinSeleccionado());
        return todosComodines;
    }
}
