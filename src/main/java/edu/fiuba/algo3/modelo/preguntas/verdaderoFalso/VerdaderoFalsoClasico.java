package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.OpcionCorrectaYaAgregadaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.OpcionIncorrectaYaAgregadaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalsoClasico extends Pregunta {

    public VerdaderoFalsoClasico(String titulo, String tituloCorrecta, String tiuloIncorrecta) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcial()));

        this.agregarOpcionCorrecta(tituloCorrecta);
        this.agregarOpcionIncorrecta(tiuloIncorrecta);
    }

    private void agregarOpcionCorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeCorrecto()));
    }

    private void agregarOpcionIncorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeIncorrecto()));
    }
}
