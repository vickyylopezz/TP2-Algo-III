package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class VerdaderoFalsoConPenalidad extends Pregunta {

    private OpcionClasica opcionCorrecta;
    private OpcionClasica opcionIncorrecta;

    public VerdaderoFalsoConPenalidad(String titulo){
        super(titulo, new ConPenalidad(new CalculadorPuntajeParcial()));
        this.opcionCorrecta = null;
        this.opcionIncorrecta = null;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionCorrecta != null){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        this.opcionCorrecta = new OpcionClasica(opcionTitulo, this.estado.puntajeCorrecto());
        this.opciones.add(this.opcionCorrecta);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionIncorrecta != null){
            throw new PreguntaError("Ya existe una opcion incorrecta");
        }
        this.opcionIncorrecta = new OpcionClasica(opcionTitulo, this.estado.puntajeIncorrecto());
        this.opciones.add(this.opcionIncorrecta);
    }

}
