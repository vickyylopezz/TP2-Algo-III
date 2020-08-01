package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.Tiempo;

public class Jugada {

    private final Pregunta pregunta;
    private final Jugador jugador;

    public Jugada(Pregunta pregunta, Jugador jugador) throws JugadaError {
        if (pregunta == null) throw new JugadaError("Pregunta indefinida");
        if (jugador == null) throw new JugadaError("Jugador indefinido");

        this.pregunta = pregunta;
        this.jugador = jugador;
    }

    public String tituloPregunta() { return this.pregunta.obtenerTitulo(); }

    public Tiempo tiempoPregunta() { return this.pregunta.obtenerTiempo(); }

    // tituloPregunta() -> String
    // tiempoPregunta() -> Tiempo

    // iniciarTiempo() -> nil

        // tiempoTranscurrido() -> Tiempo
        // tiempoRestante() -> Tiempo

        // opciones() -> []Opcion
        // comodines() -> []Comodin
        // comdoinesValidos() -> []Comodin

        // seleccionarOpcion(Opcion) -> nil
        // aplicarComodin(Comodin) -> nil

    // finalizarTiempo() -> nil

    // obtenerRespuestas() -> []Respuesta
    // obtenerComodines() -> []Comodin
}
