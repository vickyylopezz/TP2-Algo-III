package edu.fiuba.algo3.modelo.preguntas.groupChoice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeGruposError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionGroupChoice;
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
            OpcionGroupChoice opcionIncorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeIncorrecto(),grupos.get(1));
            OpcionGroupChoice opcionCorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeCorrecto(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(1).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }else{
            OpcionGroupChoice opcionIncorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeIncorrecto(),grupos.get(0));
            OpcionGroupChoice opcionCorrecta = new OpcionGroupChoice(titulo,this.estado.puntajeCorrecto(),grupo);
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

    @Override
    public void extraerOpciones(JsonObject object) throws PreguntaError {
        JsonArray nombresGrupos = object.getAsJsonArray("grupos");
        if (nombresGrupos == null) { return; /* EXCEPCION */ }
        JsonArray grupo1 = object.getAsJsonArray("grupo1");
        if (grupo1 == null) { return; /* EXCEPCION */ }
        JsonArray grupo2 = object.getAsJsonArray("grupo2");
        if (grupo2 == null) { return; /* EXCEPCION */ }

        definirGrupo(nombresGrupos.get(0).getAsString());
        definirGrupo(nombresGrupos.get(1).getAsString());
        ArrayList<Grupo> grupos = obtenerGrupos();

        for (JsonElement opcion: grupo1){
            agregarOpcion(grupos.get(0), opcion.getAsString());
        }
        for (JsonElement opcion: grupo2){
            agregarOpcion(grupos.get(1), opcion.getAsString());
        }
    }
}