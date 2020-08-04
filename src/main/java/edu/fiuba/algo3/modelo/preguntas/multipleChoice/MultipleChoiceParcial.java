package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcialEstricto;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.util.punto.*;

public class MultipleChoiceParcial extends Pregunta {

    public MultipleChoiceParcial(String titulo) {
        super(titulo, new ConPenalidad(new CalculadorPuntajeParcialEstricto()));
        /*
        if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }
        this.segundos = segundos;
        this.titulo = titulo;
        */
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, new PuntoPositivo());
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new PreguntaError("Se alcanzo el maximo de opciones para esta pregunta");
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, new PuntoNulo());
        this.opciones.add(opcion);
    }

    /*public String titulo() {
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
    public void seleccionarOpcion(Opcion opcion) throws RespuestaError {
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar() {
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
            if (opcion.obtenerPunto().getValor().equals(new PuntoNulo().getValor())){
                return new PuntoNulo();
            }
            puntajeParcial.agregarPunto(opcion.obtenerPunto());
        }
        return puntajeParcial;
    }*/
}
