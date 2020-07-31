package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;

import java.util.ArrayList;
import java.util.Date;

public class MultipleChoiceClasico implements Pregunta {
    private String titulo;
    private Integer segundos;
    private Integer puntajeCorrecto;
    private ArrayList<Opcion> opciones;
    private Respuesta respuestaActual;

    // posible refactorizactorizacion mover el inicio de cada
    // respuesta a la misma respuesta.
    private Date inicioRespuestaActual;

    public MultipleChoiceClasico(String titulo, Integer segundos) throws PreguntaError {
        if (segundos < 0) {
            throw new PreguntaError("Segundo de pregunta negativo");
        }

        this.titulo = titulo;
        this.segundos = segundos;
        this.puntajeCorrecto = 0;
        this.opciones = new ArrayList<>();
        this.respuestaActual = null;
        this.inicioRespuestaActual = null;
    }

    public String obtenerTitulo() {
        return this.titulo;
    }

    public Integer obtenerSegundos() { return this.segundos; }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    public void agregarOpcionIncorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new PreguntaError("Capacidad maxima de opciones alcanzadas");
        }

        Opcion opcion = new Opcion(titulo, 0);
        this.opciones.add(opcion);
    }

    public void agregarOpcionCorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new PreguntaError("Capacidad maxima de opciones alcanzadas");
        }

        Opcion opcion = new Opcion(titulo, 1);
        this.opciones.add(opcion);
        this.puntajeCorrecto++;
    }

    // Implementacion interface Pregunta
    @Override
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError {
        if (opciones == null) { return 0; }

        int opcionesCorrectas = 0;

        for (Opcion opcion: opciones) {
            if (!this.opciones.contains(opcion)) {
                throw new PreguntaError(opcion.toString() + ", no se encuentra guardada");
            }
            // Como el valor correcto de la opcion es 1 y el incorrecto
            // es cero solamente incrementa 1 cuando es correcta la opcion.
            opcionesCorrectas += opcion.obtenerPunto();
        }

        boolean mismaCantidadDeOpciones = opciones.size() == opcionesCorrectas;
        boolean puntajeTotalCorrecto = this.puntajeCorrecto == opcionesCorrectas;
        boolean todasOpcionesCorrecta = mismaCantidadDeOpciones && puntajeTotalCorrecto;

        return todasOpcionesCorrecta ? 1 : 0;
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
        this.inicioRespuestaActual = new Date();
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError {
        if (this.respuestaActual == null) {
            throw new PreguntaError("No se Inicio el jugador");
        }
        if (!this.opciones.contains(opcion)) {
            throw new PreguntaError("La opcion no se encuentra guardada");
        }
        long segundosTranscurridos = (new Date().getTime() - this.inicioRespuestaActual.getTime());
        if (segundosTranscurridos >= this.segundos * 1000) {
            throw new PreguntaError("Tiempo de respuesta excedido");
        }
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar()  throws PreguntaError {
        if (this.respuestaActual == null) {
            throw new PreguntaError("No se Inicio el jugador");
        }

        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        this.inicioRespuestaActual = null;
        return resultado;
    }
}
