package edu.fiuba.algo3.modelo.preguntas.groupChoice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeGruposError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;

import java.util.ArrayList;

public class GroupChoice extends Pregunta {

    private final ArrayList<Grupo> grupos;

    public GroupChoice(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcial()));

        this.grupos = new ArrayList<>();
    }

    public ArrayList<Grupo> obtenerGrupos() { return this.grupos; }

    public void definirGrupo(String titulo) throws PreguntaError {
        if(this.grupos.size() == 2){
            throw new CantidadMaximaDeGruposError();
        }
        Grupo grupo = new Grupo(titulo);
        this.grupos.add(grupo);
    }

    public void agregarOpcion(Grupo grupo, String titulo) throws PreguntaError{
        if(opciones.size() == 12){
            throw new CantidadMaximaDeOpcionesError();
        }
        if(grupo.equals(grupos.get(0))){
            Opcion opcionIncorrecta = new Opcion(titulo,this.estado.puntajeIncorrecto(),grupos.get(1));
            Opcion opcionCorrecta = new Opcion(titulo,this.estado.puntajeCorrecto(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(1).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }else{
            Opcion opcionIncorrecta = new Opcion(titulo,this.estado.puntajeIncorrecto(),grupos.get(0));
            Opcion opcionCorrecta = new Opcion(titulo,this.estado.puntajeCorrecto(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(0).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }

        // Iba a hacer cambios en group choice en el manejo de opciones
        // Las opciones se guardan dos veces?
        /*if (opciones.size() == 6){
            throw new CantidadMaximaDeOpcionesError();
        }
        OpcionGroupChoice opcionIncorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeIncorrecto(),grupos.get(1));
        OpcionGroupChoice opcionCorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeCorrecto(),grupo);
        grupo.agregarOpcion(opcionCorrecta);
        grupos.get(1).agregarOpcion(opcionIncorrecta);
        opciones.add(opcionCorrecta);
        opciones.add(opcionIncorrecta);*/
    }
}