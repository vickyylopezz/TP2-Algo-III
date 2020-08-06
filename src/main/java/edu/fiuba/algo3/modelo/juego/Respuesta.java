package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.excepciones.respuesta.ComodinYaAplicadoError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.OpcionInexistenteError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.OpcionYaElegidaError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import java.util.ArrayList;

public class Respuesta {

    private final Pregunta pregunta;
    private final Jugador jugador;
    private final ArrayList<Opcion> opciones;
    private final ArrayList<Comodin> comodines;
    //private Boolean abierta;
    //private Boolean cerrada;
    
    public Respuesta(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
        this.opciones = new ArrayList<>();
        this.comodines = new ArrayList<>();
        //this.abierta = false;
        //this.cerrada = false;
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

    /*
    public void abrir() throws RespuestaError {
        if (this.abierta() || this.cerrada()) {
            throw new RespuestaError("La respuesta ya fue abierta");
        }
        this.abierta = true; }

    public boolean cerrada() { return this.cerrada; }

    public Boolean abierta() { return this.abierta; }

    public void cerrar() throws RespuestaError {
        if (!this.abierta()) {
            throw new RespuestaError("La respuesta no ha sido abierta");
        }
        this.abierta = false;
        this.cerrada = true;
    }
    */

    public void agregarOpcion(Opcion opcion) throws RespuestaError {
        /*if (!this.abierta()) {
            throw new RespuestaError("La respuesta no esta abierta");
        }*/
        if (this.opciones.contains(opcion)){
            throw new OpcionYaElegidaError();
        }
        this.opciones.add(opcion);
    }

    public void sacarOpcion(Opcion opcion) throws RespuestaError {
        /*if (!this.abierta()) {
            throw new RespuestaError("La respuesta no esta abierta");
        }*/
        if (!this.opciones.contains(opcion)) {
            throw new OpcionInexistenteError();
        }
        this.opciones.remove(opcion);
    }

    public void aplicarComodin(Comodin comodin) throws RespuestaError {
        /*if (!this.cerrada()) {
            throw new RespuestaError("La respuesta no esta cerrada");
        }*/
        if (this.comodines.contains(comodin)){
            throw new ComodinYaAplicadoError();
        }
        this.comodines.add(comodin);
    }

    public boolean esCorrecta() {
        return this.pregunta.opcionesCorrectas(this.opcionesElegidas());
    }

    public boolean validarComodin(Comodin comodin) {
        return this.jugador.obtenerComodines().contains(comodin);
    }

    public void eliminarComodin(Comodin comodin) throws JugadorError {
        this.jugador.sacarComodin(comodin);
    }
}
