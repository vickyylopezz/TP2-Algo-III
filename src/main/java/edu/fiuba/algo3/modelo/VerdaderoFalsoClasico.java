package edu.fiuba.algo3.Composite;

import edu.fiuba.algo3.modelo.*;

import java.util.ArrayList;

public class VerdaderoFalsoClasico implements Pregunta {

    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;
    private String titulo;
    private Respuesta respuestaActual;
    private Integer segundos;

    public VerdaderoFalsoClasico(String pregunta, Integer segundos) throws PreguntaError {
        if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }
        this.opcionCorrecta = null;
        this.opcionIncorrecta = null;
        this.titulo = pregunta;
        this.segundos = segundos;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionCorrecta != null){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        Opcion opcion = new Opcion(opcionTitulo, new PuntoPositivo());
        this.opcionCorrecta = opcion;
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionIncorrecta != null){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        Opcion opcion = new Opcion(opcionTitulo, new PuntoNulo());
        this.opcionIncorrecta = opcion;
    }

    @Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        if (this.opcionCorrecta == null || this.opcionIncorrecta == null) {
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
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) {
        if (opciones.size() == 0){
            return new PuntoNulo();
        }
        return opciones.get(0).getValor();
    }

    public ArrayList<Opcion> obtenerOpciones() {
        ArrayList<Opcion> opciones = new ArrayList<Opcion>();
        opciones.add(this.opcionCorrecta);
        opciones.add(this.opcionIncorrecta);
        return opciones;
    }

    public String titulo() {
        return this.titulo;
    }
}
