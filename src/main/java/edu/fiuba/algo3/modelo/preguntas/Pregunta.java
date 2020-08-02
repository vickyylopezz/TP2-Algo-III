package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public interface Pregunta {
    Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    void iniciar(Jugador jugador) throws PreguntaError;
    void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError;
    Respuesta confirmar() throws PreguntaError;
}
