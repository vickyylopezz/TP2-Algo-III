package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalsoConPenalidad extends Pregunta {

    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;

    public VerdaderoFalsoConPenalidad(String titulo){
        super(titulo, new ConPenalidad(new CalculadorPuntajeParcial()));
        this.opcionCorrecta = null;
        this.opcionIncorrecta = null;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionCorrecta != null){
            throw new CantidadMaximaDeOpcionesError();
        }
        this.opcionCorrecta = new Opcion(opcionTitulo, this.estado.puntajeCorrecto());
        this.opciones.add(this.opcionCorrecta);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionIncorrecta != null){
            throw new CantidadMaximaDeOpcionesError();
        }
        this.opcionIncorrecta = new Opcion(opcionTitulo, this.estado.puntajeIncorrecto());
        this.opciones.add(this.opcionIncorrecta);
    }

    @Override
    public String mostrarTipoPregunta() {
        return "Verdadero Falso con Penalidad";
    }

    @Override
    public void extraerOpciones(JsonObject object) throws PreguntaError {
        boolean respuesta = object.get("respuesta").getAsBoolean();

        if (respuesta) {
            agregarOpcionCorrecta("Verdadero");
            agregarOpcionIncorrecta("Falso");
        } else {
            agregarOpcionCorrecta("Falso");
            agregarOpcionIncorrecta("Verdadero");
        }
    }
}
