package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.MiliSegundo;
import edu.fiuba.algo3.modelo.util.tiempo.Tiempo;

import java.util.ArrayList;
import java.util.Date;

public class Jugada {

    private final Pregunta pregunta;
    private final Jugador jugador;
    private final Respuesta respuesta;

    public Jugada(Pregunta pregunta, Jugador jugador) throws JugadaError {
        if (pregunta == null) throw new JugadaError("Pregunta indefinida");
        if (jugador == null) throw new JugadaError("Jugador indefinido");

        this.pregunta = pregunta;
        this.jugador = jugador;
        this.respuesta = new Respuesta(pregunta, jugador);
    }

    public String tituloPregunta() { return this.pregunta.obtenerTitulo(); }

    public Tiempo tiempoPregunta() { return this.pregunta.obtenerTiempo(); }

    public Tiempo tiempoTrancurrido() throws JugadaError {
        if (!this.respuesta.abierta()) {
            throw new JugadaError("El tiempo no ha sido inicializado");
        }
        Tiempo tiempoActual = MiliSegundo.crearConFecha(new Date());
        return tiempoActual.restar(this.respuesta.obtenerTiempoInicial());
    }

    public void iniciarTiempo() {
        this.respuesta.abrir();
    }

    public Tiempo tiempoRestante() throws JugadaError {
        return this.pregunta.obtenerTiempo().restar(this.tiempoTrancurrido());
    }

    // tituloPregunta() -> String
    // tiempoPregunta() -> Tiempo

    // iniciarTiempo() -> nil

        // tiempoTranscurrido() -> Tiempo
        // tiempoRestante() -> Tiempo

        // opciones() -> []Opcion
        // comodines() -> []Comodin
        // comodoinesValidos() -> []Comodin

        // seleccionarOpcion(Opcion) -> nil
        // aplicarComodin(Comodin) -> nil

    // finalizarTiempo() -> nil

    // obtenerRespuestas() -> []Respuesta
    // obtenerComodines() -> []Comodin
}
