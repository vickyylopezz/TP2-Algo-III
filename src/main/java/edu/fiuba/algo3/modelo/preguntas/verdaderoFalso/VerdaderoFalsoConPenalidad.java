package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalsoConPenalidad extends Pregunta {

    public VerdaderoFalsoConPenalidad(String titulo, String tituloCorrecta, String tiuloIncorrecta){
        super(titulo, new ConPenalidad(new CalculadorPuntajeParcial()));

        this.agregarOpcionCorrecta(tituloCorrecta);
        this.agregarOpcionIncorrecta(tiuloIncorrecta);
    }

    public void agregarOpcionCorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeCorrecto()));
    }

    private void agregarOpcionIncorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeIncorrecto()));
    }

    @Override
    public String obtenerTipo() { return "Verdadero Falso con Penalidad"; }
}
