package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.OpcionClasica;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

import java.util.ArrayList;

public class VerdaderoFalsoConPenalidad implements Pregunta {
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private String titulo;
    private Respuesta respuestaActual = null;
    private Penalidad estadoPenalidad = new ConPenalidad();

    public VerdaderoFalsoConPenalidad(String titulo,boolean VerdaderoCorrecta){
        this.titulo = titulo;
        if(VerdaderoCorrecta){
            Opcion opcionVerdadero = new OpcionClasica("Verdadero", this.estadoPenalidad.puntajeOpcionCorrecta());
            Opcion opcionFalso = new OpcionClasica("Falso", this.estadoPenalidad.puntajeOpcionIncorrecta());
            this.opciones.add(opcionVerdadero);
            this.opciones.add(opcionFalso);
        }else{
            Opcion opcionVerdadero = new OpcionClasica("Verdadero", this.estadoPenalidad.puntajeOpcionIncorrecta());
            Opcion opcionFalso = new OpcionClasica("Falso", this.estadoPenalidad.puntajeOpcionCorrecta());
            this.opciones.add(opcionVerdadero);
            this.opciones.add(opcionFalso);
        }
    }

    public String obtenerTitulo() { return this.titulo; }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opciones)  {
        return opciones.get(0).obtenerPunto();
    }

    @Override
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
    }

    public boolean conPenalidad(){
        return this.estadoPenalidad.conPenalidad();
    }
}
