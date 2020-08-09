package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import edu.fiuba.algo3.modelo.excepciones.preguntas.OpcionCorrectaYaAgregadaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.OpcionIncorrectaYaAgregadaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalsoClasico extends Pregunta {

    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;

    public VerdaderoFalsoClasico(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcial()));
        this.opcionCorrecta = null;
        this.opcionIncorrecta = null;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionCorrecta != null){
            throw new OpcionCorrectaYaAgregadaError();
        }
        this.opcionCorrecta = new Opcion(opcionTitulo, this.estado.puntajeCorrecto());
        opciones.add(this.opcionCorrecta);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionIncorrecta != null){
            throw new OpcionIncorrectaYaAgregadaError();
        }
        this.opcionIncorrecta = new Opcion(opcionTitulo, this.estado.puntajeIncorrecto());
        opciones.add(this.opcionIncorrecta);
    }

}
