package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeClasico;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

public class MultipleChoiceClasico extends Pregunta {

    public MultipleChoiceClasico(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeClasico()));
    }

    public void agregarOpcionIncorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new PreguntaError("Capacidad maxima de opciones alcanzadas");
        }

        Opcion opcion = new OpcionClasica(titulo, new PuntoNulo());
        this.opciones.add(opcion);
    }

    public void agregarOpcionCorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new PreguntaError("Capacidad maxima de opciones alcanzadas");
        }

        Opcion opcion = new OpcionClasica(titulo, new PuntoPositivo());
        this.opciones.add(opcion);
    }

    /*

    // Implementacion interface Pregunta
    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError {
        if (opciones == null) { return new PuntoNulo(); }

        int opcionesCorrectas = 0;

        for (Opcion opcion: opciones) {
            if (!this.opciones.contains(opcion)) {
                throw new PreguntaError(opcion.toString() + ", no se encuentra guardada");
            }
            // Como el valor correcto de la opcion es 1 y el incorrecto
            // es cero solamente incrementa 1 cuando es correcta la opcion.
            opcionesCorrectas += opcion.obtenerPunto().getValor();
        }

        boolean mismaCantidadDeOpciones = opciones.size() == opcionesCorrectas;
        boolean puntajeTotalCorrecto = this.puntajeCorrecto == opcionesCorrectas;
        boolean todasOpcionesCorrecta = mismaCantidadDeOpciones && puntajeTotalCorrecto;

        return todasOpcionesCorrecta ? new PuntoPositivo() : new PuntoNulo();
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
    }*/

}
