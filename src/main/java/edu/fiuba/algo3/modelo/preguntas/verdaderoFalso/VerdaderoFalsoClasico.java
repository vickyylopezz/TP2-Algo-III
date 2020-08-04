package edu.fiuba.algo3.modelo.preguntas.verdaderoFalso;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;

import java.util.ArrayList;

public class VerdaderoFalsoClasico extends Pregunta {

    private OpcionClasica opcionCorrecta;
    private OpcionClasica opcionIncorrecta;
    protected ArrayList<Opcion> opciones;
    //private Integer segundos;

    public VerdaderoFalsoClasico(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcial()));
        this.opcionCorrecta = null;
        this.opcionIncorrecta = null;
        /*this.segundos = segundos;
        if (segundos < 0){
            throw new PreguntaError("Los segundos no pueden ser negativos");
        }*/
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionCorrecta != null){
            throw new PreguntaError("Ya existe una opcion correcta");
        }
        this.opcionCorrecta = new OpcionClasica(opcionTitulo, this.estado.puntajeCorrecto());
        opciones.add(this.opcionCorrecta);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (opcionIncorrecta != null){
            throw new PreguntaError("Ya existe una opcion incorrecta");
        }
        this.opcionIncorrecta = new OpcionClasica(opcionTitulo, this.estado.puntajeIncorrecto());
        opciones.add(this.opcionIncorrecta);
    }

    /*@Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        if (this.opcionCorrecta == null || this.opcionIncorrecta == null) {
            throw new PreguntaError("Cantidad de opciones guardadas invalida");
        }
        if (jugador == null) {
            throw new PreguntaError("Jugador nulo");
        }
        if (this.respuestaActual != null) {
            throw new PreguntaError("No se ha cerrado el ultimo jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError {
        try {
            this.respuestaActual.agregarOpcion(opcion);
        } catch (RespuestaError respuestaError) {
            throw new PreguntaError("No se puede agregar la opcion: " + respuestaError.getMessage());
        }
    }

    @Override
    public Respuesta confirmar() {
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }*/

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones) {
        if (opciones.size() == 0){
            return new PuntoNulo();
        }
        return opciones.get(0).obtenerPunto();
    }

    /*public String titulo() {
        return this.titulo;
    }*/
}
