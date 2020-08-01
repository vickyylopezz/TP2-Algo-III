package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public class GroupChoice implements Pregunta{
    private final ArrayList<Grupo> grupos = new ArrayList<>();
    private final ArrayList<OpcionGroupChoice> opciones = new ArrayList<>();
    private Respuesta respuestaActual;

    private Punto puntajeOpcion(OpcionGroupChoice opcion){
        if(opcion.grupoCoincide()){
            return new PuntoPositivo();
        }
        return new PuntoNulo();
    }

    public ArrayList<OpcionGroupChoice> obtenerOpciones() {
        ArrayList<OpcionGroupChoice> nuevoArray = new ArrayList<>();
        for(OpcionGroupChoice opcion : opciones){
            OpcionGroupChoice copia = opcion.clone();
            nuevoArray.add(copia);
        }
        return nuevoArray;
    }

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
        OpcionGroupChoice opcion = new OpcionGroupChoice(titulo,new PuntoNulo(),grupo);
        grupo.agregarOpcion(opcion);
        opciones.add(opcion);

    }

    public void seleccionarOpcionEnGrupo(Grupo grupo, OpcionGroupChoice opcion) throws RespuestaError, PreguntaError {
        if(respuestaActual == null){
            throw new PreguntaError("No se ha iniciado el jugador");
        }
        opcion.agregarGrupo(grupo);
        this.seleccionarOpcion(opcion);
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
            throw new PreguntaError("No existe el Jugador");
        }
        if(this.respuestaActual != null){
            throw new PreguntaError("Debe confirmar antes de iniciar con otro jugador");
        }
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws RespuestaError {
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

}
