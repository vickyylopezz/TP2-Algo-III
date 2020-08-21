package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.MultipleChoice;

public class ParserMultipleChoice implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws LectorFormatoDePreguntaError, PreguntaError {
        JsonElement tipoJson = objeto.get("tipo");
        if (tipoJson == null) {
            throw new LectorFormatoDePreguntaError("Tipo de pregunta no definido");
        }
        String tipo = tipoJson.getAsString();
        if (!tipo.equals("MCClasico") && !tipo.equals("MCPenalidad") && !tipo.equals("MCParcial")) {
            throw new LectorFormatoDePreguntaError("Tipo pregunta invalido");
        }

        JsonElement preguntaJson = objeto.get("pregunta");
        if (preguntaJson == null) {
            throw new LectorFormatoDePreguntaError("Atributo 'pregunta' no definido");
        }
        String nombrePregunta = preguntaJson.getAsString();

        MultipleChoice pregunta = null;
        switch (tipo) {
            case "MCClasico":
                pregunta = MultipleChoice.Clasico(nombrePregunta);
                break;
            case "MCPenalidad":
                pregunta = MultipleChoice.ConPenalidad(nombrePregunta);
                break;
            case "MCParcial":
                pregunta = MultipleChoice.Parcial(nombrePregunta);
                break;
        }

        JsonArray opcionesCorrectas;
        try { opcionesCorrectas = objeto.getAsJsonArray("opcionesCorrectas"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de opciones correctas tipo incorrecto");
        }
        if (opcionesCorrectas == null) {
            throw new LectorFormatoDePreguntaError("Array de opciones correctas indefinido");
        }

        JsonArray opcionesIncorrectas;
        try { opcionesIncorrectas = objeto.getAsJsonArray("opcionesIncorrectas"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de opciones incorrectas tipo incorrecto");
        }
        if (opcionesIncorrectas == null) {
            throw new LectorFormatoDePreguntaError("Array de opciones incorrectas indefinido");
        }

        for (JsonElement opcion: opcionesCorrectas) {
            pregunta.agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            pregunta.agregarOpcionIncorrecta(opcion.getAsString());
        }

        if (pregunta.obtenerOpciones().size() < 2) {
            throw new LectorFormatoDePreguntaError("Cantidad minima de opciones no alcanzada");
        }

        return pregunta;
    }
}
