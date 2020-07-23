package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class MultipleChoiseClasico implements Pregunta {
    private String titulo;
    private Integer segundos;

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

    @Override
    public Integer PuntajeConOpciones(ArrayList<Opcion> opciones) {
        return null;
    }

    @Override
    public void Iniciar(Jugador jugador) throws PreguntaError {
        if (jugador == null) {
            throw new PreguntaError("Jugador nulo");
        }
    }

    @Override
    public void SeleccionarOpcion(Opcion opcion) {

    }

    @Override
    public Respuesta Confirmar() {
        return null;
    }
}
