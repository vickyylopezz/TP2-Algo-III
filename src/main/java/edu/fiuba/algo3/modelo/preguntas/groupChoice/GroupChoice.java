package edu.fiuba.algo3.modelo.preguntas.groupChoice;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.opcion.OpcionGroupChoice;
import edu.fiuba.algo3.modelo.preguntas.penalidad.Penalidad;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.penalidad.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public class GroupChoice implements Pregunta {
    private final ArrayList<Grupo> grupos = new ArrayList<>();
    private final ArrayList<OpcionGroupChoice> opciones = new ArrayList<>();
    private Respuesta respuestaActual;
    private Penalidad estadoPenalidad = new SinPenalidad();

    public ArrayList<OpcionGroupChoice> obtenerOpciones() { return this.opciones; }

    public ArrayList<Grupo> obtenerGrupos() { return this.grupos; }

    public void definirGrupo(String titulo) throws PreguntaError {
        if(this.grupos.size() == 2){
            throw new PreguntaError("Cantidad maxima de grupos alcanzada");
        }
        Grupo grupo = new Grupo(titulo);
        this.grupos.add(grupo);
    }

    public void agregarOpcion(Grupo grupo, String titulo) throws PreguntaError {
        if(opciones.size() == 12){
            throw new PreguntaError("Maximo de opciones alcanzado");
        }
        if(grupo.equals(grupos.get(0))){
            OpcionGroupChoice opcionIncorrecta = new OpcionGroupChoice(titulo,this.estadoPenalidad.puntajeIncorrecta(),grupos.get(1));
            OpcionGroupChoice opcionCorrecta = new OpcionGroupChoice(titulo,this.estadoPenalidad.puntajeCorrecta(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(1).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }else{
            OpcionGroupChoice opcionIncorrecta = new OpcionGroupChoice(titulo,this.estadoPenalidad.puntajeIncorrecta(),grupos.get(0));
            OpcionGroupChoice opcionCorrecta = new OpcionGroupChoice(titulo,this.estadoPenalidad.puntajeCorrecta(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(0).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }
    }

    @Override
    public Punto puntajeConOpciones(ArrayList<Opcion> opcionesPuntaje){
        Puntaje puntaje = new Puntaje();
        for (Opcion opcion : opcionesPuntaje){
            puntaje.agregarPunto(opcion.obtenerPunto());
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
    public void seleccionarOpcion(Opcion opcion) throws RespuestaError, PreguntaError {
        if(respuestaActual == null){
            throw new PreguntaError("No se ha iniciado el jugador");
        }
        if (respuestaActual.obtenerOpcionesElegidas().size() == 6){
            throw new RespuestaError("Cantidad maxima de opciones elegidas");
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
