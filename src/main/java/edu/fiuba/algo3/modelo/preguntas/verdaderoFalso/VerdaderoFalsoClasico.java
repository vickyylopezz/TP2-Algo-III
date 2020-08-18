package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalsoClasico extends Pregunta {

    public VerdaderoFalsoClasico(String titulo, String tituloCorrecta, String tiuloIncorrecta) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcial()));

        //this.agregarOpcionCorrecta(tituloCorrecta);
        //this.agregarOpcionIncorrecta(tiuloIncorrecta);
    }

    private void agregarOpcionCorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeCorrecto()));
    }

    private void agregarOpcionIncorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeIncorrecto()));
    }

    @Override
    public String mostrarTipoPregunta() {
        return "Verdadero Falso Clasico";
    }

    @Override
    public void extraerOpciones(JsonObject object) {
        String respuesta = object.get("respuesta").getAsString();

        if (respuesta.equals("Verdadero")) {
            agregarOpcionCorrecta("Verdadero");
            agregarOpcionIncorrecta("Falso");
        } else {
            agregarOpcionCorrecta("Falso");
            agregarOpcionIncorrecta("Verdadero");
        }
    }

}
