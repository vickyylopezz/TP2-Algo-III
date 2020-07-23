package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public interface Pregunta {
    Integer PuntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    void Iniciar(Jugador jugador) throws PreguntaError;
    void SeleccionarOpcion(Opcion opcion) throws PreguntaError;
    Respuesta Confirmar() throws PreguntaError;
}
