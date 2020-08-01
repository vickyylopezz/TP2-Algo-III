package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
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

    public void iniciarTiempo() throws JugadaError {
        try {
            this.respuesta.abrir();
        } catch (RespuestaError e) {
            throw new JugadaError("Tiempo ya inicializado");
        }
    }

    public Tiempo tiempoRestante() throws JugadaError {
        return this.pregunta.obtenerTiempo().restar(this.tiempoTrancurrido());
    }

    public ArrayList<Opcion> opciones() {
        return this.pregunta.obtenerOpciones();
    }

    public ArrayList<Comodin> comodines() {
        return this.jugador.obtenerComodines();
    }

    public void seleccionarOpcion(Opcion opcion) throws JugadaError, PreguntaError, RespuestaError {
        if (!this.respuesta.abierta()) {
            throw new JugadaError("El tiempo no ha sido inicializado");
        }
        if (this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo ha sido finalizado");
        }
        this.pregunta.validarOpcion(opcion);
        this.respuesta.agregarOpcion(opcion);
    }

    public void finalizarTiempo() throws JugadaError {
        if (!this.respuesta.abierta()) { throw new JugadaError("Tiempo no inicializado"); }
        if (this.respuesta.cerrada()) { throw new JugadaError("Tiempo ya finalizado"); }
        this.respuesta.cerrar();
    }

    public Respuesta obtenerRespuesta() throws JugadaError {
        if (!this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo ha sido finalizado");
        }
        return this.respuesta;
    }

    // tituloPregunta() -> String
    // tiempoPregunta() -> Tiempo

    // opciones() -> []Opcion
    // opcionesValidas() -> []Opcion
    // comodines() -> []Comodin
    // comodinesValidos() -> []Comodin

    // iniciarTiempo() -> nil

        // tiempoTranscurrido() -> Tiempo
        // tiempoRestante() -> Tiempo

        // seleccionarOpcion(Opcion) -> nil
        // deseleccionarOpcion(Opcion) -> nil
        // aplicarComodin(Comodin) -> nil
        // desaplicarComodin(Comodin) -> nil

        // opcionesSeleccionadas() -> []Opcion
        // comodinesSeleccionados() -> []Comodin

    // finalizarTiempo() -> nil

    // obtenerRespuestas() -> []Respuesta
    // obtenerComodines() -> []Comodin
}
