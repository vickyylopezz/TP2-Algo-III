package edu.fiuba.algo3.modelo;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public interface Pregunta {
    Integer puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    void iniciar(Jugador jugador) throws PreguntaError;
    void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError;
    Respuesta confirmar() throws PreguntaError;

    String obtenerTitulo();
    ArrayList<Opcion> obtenerOpciones();
    void extraerOpciones(JsonObject object) throws PreguntaError;
}
