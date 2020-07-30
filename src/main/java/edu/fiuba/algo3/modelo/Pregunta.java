package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Composite.Punto;

import java.util.ArrayList;

public interface Pregunta {
    Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    void iniciar(Jugador jugador) throws PreguntaError;
    void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError;
    Respuesta confirmar() throws PreguntaError;
}
