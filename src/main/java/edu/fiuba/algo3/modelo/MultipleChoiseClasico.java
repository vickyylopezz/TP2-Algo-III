package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class MultipleChoiseClasico implements Pregunta {
    private String titulo;
    private Integer segundos;
    private Integer opcionesCorrectas = 0;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Respuesta respuestaActual = null;

    public MultipleChoiseClasico(String titulo, Integer segundos) throws MultipleChoiseError {
        if (segundos < 0) {
            throw new MultipleChoiseError("Segundo de pregunta negativo");
        }

        this.titulo = titulo;
        this.segundos = segundos;
    }

    public String ObtenerTitulo() {
        return this.titulo;
    }

    public ArrayList<Opcion> ObtenerOpciones() {
        return this.opciones;
    }

    public void AgregarOpcion(String titulo) {
        Opcion opcion = new Opcion(titulo, 0);
        this.opciones.add(opcion);
    }

    public void AgregarOpcionCorrecta(String titulo) {
        Opcion opcion = new Opcion(titulo, 1);
        this.opciones.add(opcion);
        this.opcionesCorrectas++;
    }

    @Override
    public Integer PuntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError {
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
    public void Iniciar(Jugador jugador) throws PreguntaError {
        if (jugador == null) {
            throw new PreguntaError("Jugador nulo");
        }
        if (this.respuestaActual != null) {
            throw new PreguntaError("No se ha cerrado el ultimo jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void SeleccionarOpcion(Opcion opcion) throws PreguntaError {
        if (this.respuestaActual == null) {
            throw new PreguntaError("No se Inicio el jugador");
        }
        if (!this.opciones.contains(opcion)) {
            throw new PreguntaError("La opcion no se encuentra guardada");
        }
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta Confirmar() {
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }
}
