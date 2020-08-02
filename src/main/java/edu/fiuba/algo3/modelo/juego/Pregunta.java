package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.estados.Penalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

import java.util.ArrayList;

public abstract class Pregunta {

    private final String titulo;
    private final ArrayList<Opcion> opciones = new ArrayList<>();
    protected final Penalidad estadoPenalidad;
   /* protected final Calculador calculadorPuntaje = null;*/

    protected Pregunta(String titulo, Penalidad penalidad){
        this.titulo = titulo;
        this.estadoPenalidad = penalidad;
    }

    public abstract void agregarOpcionCorrecta(String titulo) throws PreguntaError;
    public abstract void agregarOpcionIncorrecta(String titulo) throws PreguntaError;

    public abstract Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    //protected abstract void iniciar(Jugador jugador) throws PreguntaError;
    //public abstract void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError;
    //abstract Respuesta confirmar() throws PreguntaError;

    public String obtenerTitulo(){
        return this.titulo;
    }

    public ArrayList<Opcion> obtenerOpciones(){
        return this.opciones;
    }

    public boolean conPenalizacion() {
        return this.estadoPenalidad.conPenalidad();
    }

}
