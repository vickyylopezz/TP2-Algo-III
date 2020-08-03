package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.estados.Penalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

import java.util.ArrayList;

public class VerdaderoFalsoConPenalidad extends Pregunta {

    private OpcionClasica opcionCorrecta;
    private OpcionClasica opcionIncorrecta;
    protected ArrayList<Opcion> opciones;

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

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones)  {
        return opciones.get(0).obtenerPunto();
    }



    /*@Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        if(jugador == null){
            throw new PreguntaError("No existe el jugador");
        }
        if(this.respuestaActual != null){
            throw new PreguntaError("Debe confirmar antes de iniciar con otro jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError, RespuestaError {
        if(respuestaActual == null){
            throw new PreguntaError("No se ha iniciado el jugador");
        }
        if(respuestaActual.obtenerOpcionesElegidas().size() >= 1){
            throw new PreguntaError("Solo puede elegir una opcion");
        }
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar() throws PreguntaError {
        if(respuestaActual == null){
            throw new PreguntaError("No se ha iniciado la pregunta");
        }
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }*/

}
