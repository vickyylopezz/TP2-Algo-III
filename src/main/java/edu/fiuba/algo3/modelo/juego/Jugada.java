package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.MiliSegundo;
import edu.fiuba.algo3.modelo.util.tiempo.Tiempo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Jugada {

    private final Pregunta pregunta;
    private final Jugador jugador;
    private final Respuesta respuesta;
    private final ArrayList<Comodin> comodines;

    public Jugada(Pregunta pregunta, Jugador jugador) throws JugadaError {
        if (pregunta == null) throw new JugadaError("Pregunta indefinida");
        if (jugador == null) throw new JugadaError("Jugador indefinido");

        this.comodines = new ArrayList<>();
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

    public void deseleccionarOpcion(Opcion opcion) throws JugadaError, PreguntaError, RespuestaError {
        if (!this.respuesta.abierta()) {
            throw new JugadaError("El tiempo no ha sido inicializado");
        }
        if (this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo ha sido finalizado");
        }
        this.pregunta.validarOpcion(opcion);
        this.respuesta.sacarOpcion(opcion);
    }

    public void seleccionarComodin(Comodin comodin) throws JugadaError, JugadorError, ComodinError {
        if (!this.respuesta.abierta()) {
            throw new JugadaError("El tiempo no ha sido inicializado");
        }
        if (this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo ha sido finalizado");
        }
        this.jugador.validarComodin(comodin);
        comodin.validarPregunta(this.pregunta);
        if (this.comodines.contains(comodin)) {
            throw new JugadaError("El comodin ya se encuentra aplicado");
        }
        this.comodines.add(comodin);
    }

    public ArrayList<Comodin> comodinesSeleccionados() throws JugadaError {
        if (!this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo no ha finalizado");
        }
        return new ArrayList<>(this.comodines);
    }

    public void deseleccionarComodin(Comodin comodin) throws JugadaError {
        if (!this.respuesta.abierta()) {
            throw new JugadaError("El tiempo no ha sido inicializado");
        }
        if (this.respuesta.cerrada()) {
            throw new JugadaError("El tiempo ha sido finalizado");
        }
        if (this.comodines.contains(comodin)) {
            this.comodines.remove(comodin);
            return;
        }
        throw new JugadaError("El comodin no se encuentra aplicado");
    }

    //OK tituloPregunta() -> String
    //OK tiempoPregunta() -> Tiempo

    //OK opciones() -> []Opcion
    // opcionesValidas() -> []Opcion
    //OK comodines() -> []Comodin
    // comodinesValidos() -> []Comodin

    //OK iniciarTiempo() -> nil

        //OK tiempoTranscurrido() -> Tiempo
        //OK tiempoRestante() -> Tiempo

        //OK seleccionarOpcion(Opcion) -> nil
        //OK deseleccionarOpcion(Opcion) -> nil
        //OK seleccionarComodin(Comodin) -> nil
        //OK deseleccionarComodin(Comodin) -> nil

        // opcionesSeleccionadas() -> []Opcion
        //OK comodinesSeleccionados() -> []Comodin

    //OK finalizarTiempo() -> nil

    //OK obtenerRespuestas() -> []Respuesta
}
