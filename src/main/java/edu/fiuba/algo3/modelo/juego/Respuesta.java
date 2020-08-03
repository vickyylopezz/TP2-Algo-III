package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.comodines.Multiplicador;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class Respuesta {
    protected Pregunta pregunta;
    protected Jugador jugador;
    protected ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
    private ArrayList<Comodin> comodines = new ArrayList<>();


    public Respuesta(Pregunta pregunta, Jugador jugador) {
        this.pregunta = pregunta;
        this.jugador = jugador;
    }

    public void agregarOpcion(Opcion opcion) throws RespuestaError {
        if (this.opcionesElegidas.contains(opcion)){
            throw new RespuestaError(opcion.toString() + "ya fue elegida");
        }
        this.opcionesElegidas.add(opcion);
    }

    public ArrayList<Opcion> obtenerOpcionesElegidas() {
        return new ArrayList<>(this.opcionesElegidas);
    }

    public Punto obtenerPuntaje() throws PreguntaError {
        return this.pregunta.puntajeConOpciones(this.opcionesElegidas);
    }

    public Pregunta obtenerPregunta() {
        return this.pregunta;
    }

    public ArrayList<Comodin> obtenerComodines() {
        return this.comodines;
    }

    public void agregarComodin(Comodin comodin) {
        this.comodines.add(comodin);
    }

    public boolean esCorrecta() {
        return true;
    }
}
