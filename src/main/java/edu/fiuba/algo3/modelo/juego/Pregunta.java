package edu.fiuba.algo3.modelo.juego;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.estados.EstadoPregunta;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public abstract class Pregunta {

    private final String titulo;
    protected final ArrayList<Opcion> opciones = new ArrayList<>();
    protected final EstadoPregunta estado;

    protected Pregunta(String titulo, EstadoPregunta estado){
        this.titulo = titulo;
        this.estado = estado;
    }

    public String obtenerTitulo(){
        return this.titulo;
    }

    public Boolean conPenalidad() { return this.estado.conPenalidad(); }

    public Punto puntajeCorrecto() { return this.estado.puntajeCorrecto(); }

    public Punto puntajeIncorrecto() { return this.estado.puntajeIncorrecto(); }

    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PuntoError {
        return this.estado.puntajeConOpciones(this, opciones);
    }

    public Boolean opcionesCorrectas(ArrayList<Opcion> opciones) throws PuntoError {
        return this.estado.opcionesCorrectas(this, opciones);
    }

    public Integer cantidadOpcionesCorrectas() throws PuntoError {
        return this.estado.cantidadOpcionesCorrectas(this);
    }

    public ArrayList<Opcion> obtenerOpciones(){
        return new ArrayList<>(this.opciones);
    }

    public ArrayList<Opcion> opcionesSeleccionables(ArrayList<Opcion> seleccionadas) {
        ArrayList<Opcion> seleccionables = this.obtenerOpciones();
        for (Opcion opcion: seleccionadas) seleccionables.remove(opcion);
        return seleccionables;
    }
    public void extraerOpciones(JsonObject object) throws PreguntaError {}
}
