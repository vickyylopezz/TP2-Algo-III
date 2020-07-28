package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class VerdaderoFalsoClasico implements Pregunta {

    private ArrayList<Opcion> opciones;
    private Integer opcionesCorrectas;
    private Integer opcionesIncorrectas;
    private String titulo;
    private Respuesta respuestaActual;
    private Integer segundos;

    public VerdaderoFalsoClasico(String pregunta, Integer segundos) throws PreguntaError {
        if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }
        this.opciones = new ArrayList<Opcion>();
        this.opcionesCorrectas = 0;
        this.opcionesIncorrectas = 0;
        this.titulo = pregunta;
        this.segundos = segundos;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionesCorrectas > 0){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        Opcion opcion = new Opcion(opcionTitulo, 1);
        this.opciones.add(opcion);
        this.opcionesCorrectas++;
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionesIncorrectas > 0){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        Opcion opcion = new Opcion(opcionTitulo, 0);
        this.opciones.add(opcion);
        this.opcionesIncorrectas++;
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
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError {
        try {
            this.respuestaActual.agregarOpcion(opcion);
        } catch (RespuestaError respuestaError) {
            throw new PreguntaError("No se puede agregar la opcion: " + respuestaError.getMessage());
        }
    }

    @Override
    public Respuesta confirmar() throws PreguntaError {
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }

    @Override
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) {
        if (opciones.size() == 0){
            return 0;
        }
        return (opciones.get(0)).getValor();
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    public String titulo() {
        return this.titulo;
    }
}
