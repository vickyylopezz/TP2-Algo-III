package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;

import java.util.ArrayList;

public class ParserGroupChoice implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws PreguntaError {
        String nombrePregunta = objeto.get("pregunta").getAsString();
        GroupChoice pregunta = new GroupChoice(nombrePregunta);

        JsonArray nombresGrupos = objeto.getAsJsonArray("grupos");
        JsonArray grupo1 = objeto.getAsJsonArray("grupo1");
        JsonArray grupo2 = objeto.getAsJsonArray("grupo2");

        pregunta.definirGrupo(nombresGrupos.get(0).getAsString());
        pregunta.definirGrupo(nombresGrupos.get(1).getAsString());
        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        for (JsonElement opcion: grupo1){
            pregunta.agregarOpcion(grupos.get(0), opcion.getAsString());
        }
        for (JsonElement opcion: grupo2){
            pregunta.agregarOpcion(grupos.get(1), opcion.getAsString());
        }

        return pregunta;
    }
}
