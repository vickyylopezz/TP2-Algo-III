package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.penalidad.Penalidad;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.penalidad.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public class MultipleChoiceParcial implements Pregunta {

    private String titulo;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Respuesta respuestaActual;
    private Integer segundos;
    private Penalidad estadoPenalidad = new SinPenalidad();

    public MultipleChoiceParcial(String titulo, Integer segundos) throws PreguntaError {
        if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }
        this.segundos = segundos;
        this.titulo = titulo;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, this.estadoPenalidad.puntajeCorrecta());
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, this.estadoPenalidad.puntajeIncorrecta());
        this.opciones.add(opcion);
    }

    public String titulo() {
        return this.titulo;
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        if (this.opciones.size() < 2) {
            throw new PreguntaError("Cantidad de opciones guardadas invalida");
        }
        if (jugador == null) {
            throw new PreguntaError("Jugador nulo");
        }
        if (this.respuestaActual != null) {
            throw new PreguntaError("No se ha cerrado el ultimo jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError {
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar() throws PreguntaError {
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) {
        if (opciones.size() == 0){
            return new PuntoNulo();
        }
        Puntaje puntajeParcial = new Puntaje();
        for (Opcion opcion : opciones){
            if (opcion.obtenerPunto().getValor() == (this.estadoPenalidad.puntajeIncorrecta()).getValor()){
                return this.estadoPenalidad.puntajeIncorrecta();
            }
            puntajeParcial.agregarPunto(opcion.obtenerPunto());
        }
        return puntajeParcial;
    }

    public boolean conPenalidad(){
        return this.estadoPenalidad.conPenalidad();
    }

}
