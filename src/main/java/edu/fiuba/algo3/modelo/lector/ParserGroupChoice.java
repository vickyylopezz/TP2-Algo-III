package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;

import java.util.ArrayList;

public class ParserGroupChoice implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws PreguntaError, LectorFormatoDePreguntaError {
        JsonElement tipoJson = objeto.get("tipo");
        if (tipoJson == null) {
            throw new LectorFormatoDePreguntaError("Tipo de pregunta no definido");
        }
        String tipo = tipoJson.getAsString();
        if (!tipo.equals("Group")) {
            throw new LectorFormatoDePreguntaError("Tipo pregunta invalido");
        }

        JsonElement preguntaJson = objeto.get("pregunta");
        if (preguntaJson == null) {
            throw new LectorFormatoDePreguntaError("Atributo 'pregunta' no definido");
        }
        String nombrePregunta = preguntaJson.getAsString();

        GroupChoice pregunta = new GroupChoice(nombrePregunta);

        JsonArray nombresGruposJson;
        try { nombresGruposJson = objeto.getAsJsonArray("grupos"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de nombre de grupo tipo incorrecto");
        }
        if (nombresGruposJson == null) {
            throw new LectorFormatoDePreguntaError("Array de nombre de grupo indefinido");
        }
        if (nombresGruposJson.size() != 2) {
            throw new LectorFormatoDePreguntaError("Array de nombre de grupo cantidad invalida");
        }

        pregunta.definirGrupo(nombresGruposJson.get(0).getAsString());
        pregunta.definirGrupo(nombresGruposJson.get(1).getAsString());

        JsonArray grupoUnoJson;
        try { grupoUnoJson = objeto.getAsJsonArray("grupo1"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de grupo1 tipo incorrecto");
        }
        if (grupoUnoJson == null) {
            throw new LectorFormatoDePreguntaError("Array de grupo1 indefinido");
        }

        JsonArray grupoDosJson;
        try { grupoDosJson = objeto.getAsJsonArray("grupo2"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de grupo2 tipo incorrecto");
        }
        if (grupoDosJson == null) {
            throw new LectorFormatoDePreguntaError("Array de grupo2 indefinido");
        }

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        for (JsonElement opcion: grupoUnoJson){
            pregunta.agregarOpcion(grupos.get(0), opcion.getAsString());
        }
        for (JsonElement opcion: grupoDosJson){
            pregunta.agregarOpcion(grupos.get(1), opcion.getAsString());
        }

        return pregunta;
    }
}
