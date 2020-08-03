package edu.fiuba.algo3.modelo.preguntas.estados;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

import java.util.ArrayList;

public abstract class EstadoPregunta {

    private final CalculadorPuntaje calculadorPuntaje;

    protected EstadoPregunta(CalculadorPuntaje calculadorPuntaje) {
        this.calculadorPuntaje = calculadorPuntaje;
    }

    public Punto puntajeConOpciones(Pregunta pregunta, ArrayList<Opcion> opciones) {
        return this.calculadorPuntaje.calcular(pregunta, opciones);
    }

    public Boolean opcionesCorrectas(Pregunta pregunta, ArrayList<Opcion> opciones) {
        for (Opcion opcion: opciones) {
            if (opcion.obtenerPunto().obtenerValor() != 1) return false;
        }
        return opciones.size() == this.cantidadOpcionesCorrectas(pregunta);
    }

    public Integer cantidadOpcionesCorrectas(Pregunta pregunta) {
        Integer correctas = 0;
        for (Opcion opcion: pregunta.obtenerOpciones()) {
            if (opcion.obtenerPunto().obtenerValor() == 1) correctas++;
        }
        return correctas;
    }

    public Punto puntajeCorrecto() { return new PuntoPositivo(); }

    abstract public Boolean conPenalidad();

    abstract public Punto puntajeIncorrecto();
}
