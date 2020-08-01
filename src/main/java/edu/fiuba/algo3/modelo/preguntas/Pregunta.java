package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public abstract class Pregunta {
    public abstract Punto puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError;
    abstract void iniciar(Jugador jugador) throws PreguntaError;
    abstract void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError;
    abstract Respuesta confirmar() throws PreguntaError;
    public abstract boolean conPenalidad();
}
