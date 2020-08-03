package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.estados.Penalidad;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public abstract class MultipleChoiceConPenalidad extends Pregunta {

    private String titulo;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Respuesta respuestaActual;
   // private Integer segundos;
   protected ArrayList<Opcion> opcionesCorrectas = new ArrayList<>();

    public MultipleChoiceConPenalidad(String titulo, Penalidad penalidad) throws PreguntaError {
        super(titulo,penalidad);
        /*if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }
        this.segundos = segundos;
        this.titulo = titulo;*/
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new Opcion(opcionTitulo, new PuntoPositivo());
        this.opciones.add(opcion);
        this.opcionesCorrectas.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new Opcion(opcionTitulo, new PuntoNegativo());
        this.opciones.add(opcion);
    }

    /*public String titulo() {
        return this.titulo;
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }*/

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) {
        if (opciones.size() == 0){
            return new PuntoNulo();
        }
        Puntaje puntajeParcial = new Puntaje();
        for (Opcion opcion : opciones){
            puntajeParcial.agregarPunto(opcion.obtenerPunto());
        }
        return puntajeParcial;
    }
}
