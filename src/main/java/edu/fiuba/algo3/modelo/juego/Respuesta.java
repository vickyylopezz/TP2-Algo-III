package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
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

    public void agregarOpcion(Opcion opcion) throws RespuestaError {
        if (this.opciones.contains(opcion)){
            throw new RespuestaError(opcion.toString() + "ya fue elegida");
        }
        this.opciones.add(opcion);
    }

    public ArrayList<Comodin> comodinesAplicados() {
        return new ArrayList<>(this.comodines);
    }

    public void aplicarComodin(Comodin comodin) throws RespuestaError {
        if (this.comodines.contains(comodin)){
            throw new RespuestaError(comodin.toString() + " ya fue aplicado");
        }
        this.comodines.add(comodin);
    }

    public boolean esCorrecta() {
        return this.pregunta.opcionesCorrectas(this.opcionesElegidas());
    }

    public void sacarOpcion(Opcion opcion) throws RespuestaError {
        if (!this.opciones.contains(opcion)) {
            throw new RespuestaError(opcion.toString() + " no se encuentra en la respesta");
        }
        this.opciones.remove(opcion);
    }
}
