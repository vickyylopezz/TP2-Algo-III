package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.estados.EstadoPregunta;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class VerdaderoFalso extends Pregunta {

    public static VerdaderoFalso Clasico(String titulo, String tituloCorrecta, String tituloIncorrecta) {
        VerdaderoFalso pregunta = new VerdaderoFalso(
                titulo,
                "Verdadero Falso Clasico",
                new SinPenalidad(new CalculadorPuntajeParcial())
        );

        pregunta.agregarOpcionCorrecta(tituloCorrecta);
        pregunta.agregarOpcionIncorrecta(tituloIncorrecta);

        return pregunta;
    }

    public static VerdaderoFalso ConPenalidad(String titulo, String tituloCorrecta, String tituloIncorrecta) {
        VerdaderoFalso pregunta = new VerdaderoFalso(
                titulo,
                "Verdadero Falso Con Penalidad",
                new ConPenalidad(new CalculadorPuntajeParcial())
        );

        pregunta.agregarOpcionCorrecta(tituloCorrecta);
        pregunta.agregarOpcionIncorrecta(tituloIncorrecta);

        return pregunta;
    }

    private VerdaderoFalso(String titulo, String tipo, EstadoPregunta estado) {
        super(titulo, tipo, estado);
    }

    private void agregarOpcionCorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeCorrecto()));
    }

    private void agregarOpcionIncorrecta(String opcionTitulo) {
        this.opciones.add(new Opcion(opcionTitulo, this.puntajeIncorrecto()));
    }
}
