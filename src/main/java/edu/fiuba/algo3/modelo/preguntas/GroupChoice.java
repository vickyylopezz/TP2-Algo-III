package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public class GroupChoice implements Pregunta{
    private final ArrayList<Grupo> grupos = new ArrayList<>();
    private final ArrayList<OpcionGroupChoice> opciones = new ArrayList<>();
    private Respuesta respuestaActual;
    private Penalidad estadoPenalidad = new SinPenalidad();

    private Punto puntajeOpcion(OpcionGroupChoice opcion){
        if(opcion.grupoCoincide()){
            return new PuntoPositivo();
        }
        return new PuntoNulo();
    }
    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opcionesPuntaje){
        Puntaje puntaje = new Puntaje();
        for(Opcion opcion : opcionesPuntaje){
            puntaje.agregarPunto(this.puntajeOpcion((OpcionGroupChoice) opcion));
        }
        return puntaje;
    }

    @Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        if(this.grupos.size() < 2){
            throw new PreguntaError("Cantidad de grupos erronea");
        }
        if (jugador == null) {
            throw new PreguntaError("No exite el Jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws RespuestaError {
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar(){
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }

    public ArrayList<OpcionGroupChoice> obtenerOpciones() {return this.opciones; }

    public ArrayList<Grupo> obtenerGrupos() { return this.grupos; }

    public void definirGrupo(String titulo) throws PreguntaError {
        if(this.grupos.size() == 2){
            throw new PreguntaError("Cantidad maxima de grupos alcanzada");
        }
        Grupo grupo = new Grupo(titulo);
        this.grupos.add(grupo);
    }

    public void agregarOpcion(Grupo grupo, String titulo) throws PreguntaError {
        if(opciones.size() == 6){
            throw new PreguntaError("Cantidad maxima de opciones alcanzada");
        }
        OpcionGroupChoice opcion = new OpcionGroupChoice(titulo, this.estadoPenalidad.puntajeIncorrecta(),grupo);
        grupo.agregarOpcion(opcion);
        opciones.add(opcion);

    }

    public void seleccionarOpcionEnGrupo(Grupo grupo, OpcionGroupChoice opcion) throws RespuestaError {
        opcion.agregarGrupo(grupo);
        this.seleccionarOpcion(opcion);

    }

    public boolean conPenalidad(){
        return this.estadoPenalidad.conPenalidad();
    }
}
