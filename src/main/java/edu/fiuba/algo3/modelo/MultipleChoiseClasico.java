package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Date;

public class MultipleChoiseClasico implements Pregunta {
    private String titulo;
    private Integer segundos;
    private Integer opcionesCorrectas = 0;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Respuesta respuestaActual = null;
    private Date inicioRespuestaActual = null;

    public MultipleChoiseClasico(String titulo, Integer segundos) throws MultipleChoiseError {
        if (segundos < 0) {
            throw new MultipleChoiseError("Segundo de pregunta negativo");
        }

        this.titulo = titulo;
        this.segundos = segundos;
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
        this.opcionesCorrectas++;
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
            if (opcion.getValor() == 0) {
                return 0;
            } else {
                opcionesCorrectas++;
            }
        }

        int resultado = 0;
        if (opcionesCorrectas == this.opcionesCorrectas) {
            resultado = 1;
        }

        return resultado;
    }

    @Override
    public ArrayList<Integer> puntajeConRespuestas(ArrayList<Respuesta> respuestas) throws PreguntaError {
        if (respuestas == null) {
            throw new PreguntaError("parametro respuestas no puede ser null");
        }

        ArrayList<Integer> puntajes = new ArrayList<>();

        for (Respuesta respuesta: respuestas) {
            puntajes.add(this.puntajeConOpciones(respuesta.opcionesElegidas));
        }

        return puntajes;
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
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError {
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
        return resultado;
    }
}
