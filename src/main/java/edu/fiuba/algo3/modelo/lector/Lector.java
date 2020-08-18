package edu.fiuba.algo3.modelo.lector;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;

import java.io.*;
import java.util.ArrayList;

public class Lector {

    private final ArrayList<Pregunta> preguntas;

    public Lector() { this.preguntas = new ArrayList<>(); }

    public ArrayList<Pregunta> obtenerPreguntas(){
        return new ArrayList<>(this.preguntas);
    }

    public void extraerPreguntas(File archivo) throws IOException, PreguntaError {
        JsonReader reader = new JsonReader(new FileReader(archivo));
        JsonElement parser = JsonParser.parseReader(reader);
        this.parsearPreguntas(parser);
    }

    public void extraerPreguntas(String cadenaJson) throws PreguntaError {
        JsonElement parser = JsonParser.parseString(cadenaJson);
        this.parsearPreguntas(parser);
    }

    private void parsearPreguntas(JsonElement arrayJson) throws PreguntaError {
        JsonArray array = arrayJson.getAsJsonArray();
        for (JsonElement element: array) {
            JsonObject object = element.getAsJsonObject();
            this.preguntas.add(crearPregunta(object));
        }
    }

    private Pregunta crearPregunta(JsonObject object) throws PreguntaError {
        switch(object.get("tipo").getAsString()) {
            case "VFClasico": return this.crearVerdaderoFalsoClasico(object);
            case "VFPenalidad": return this.crearVerdaderoFalsoPenalidad(object);
            case "MCClasico": return this.crearMultipleChoiceClasico(object);
            case "MCParcial": return this.crearMultipleChoiceParcial(object);
            case "MCPenalidad": return this.crearMultipleChoicePenalidad(object);
            case "Ordered": return this.crearOrderedChoice(object);
            case "Group": return this.crearGroupChoice(object);
        }
        throw new JsonSyntaxException("Tipo de pregunta invalido");
    }

    private VerdaderoFalsoClasico crearVerdaderoFalsoClasico(JsonObject object) {
        String tituloPregunta = object.get("pregunta").toString();
        boolean respuesta = object.get("respuesta").getAsBoolean();

        String opcionCorrecta = "Verdadero";
        String opcionIncorrecta = "Falso";
        if (!respuesta) {
            opcionCorrecta = "Falso";
            opcionIncorrecta = "Verdadero";
        }

        return new VerdaderoFalsoClasico(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }

    private VerdaderoFalsoConPenalidad crearVerdaderoFalsoPenalidad(JsonObject object) {
        String tituloPregunta = object.get("pregunta").toString();
        boolean respuesta = object.get("respuesta").getAsBoolean();

        String opcionCorrecta = "Verdadero";
        String opcionIncorrecta = "Falso";
        if (!respuesta) {
            opcionCorrecta = "Falso";
            opcionIncorrecta = "Verdadero";
        }

        return new VerdaderoFalsoConPenalidad(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }

    private MultipleChoiceClasico crearMultipleChoiceClasico(JsonObject object) throws PreguntaError {
        String nombrePregunta = object.get("pregunta").toString();
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico(nombrePregunta);

        JsonArray opcionesCorrectas = object.getAsJsonArray("opcionesCorrectas");
        JsonArray opcionesIncorrectas = object.getAsJsonArray("opcionesIncorrectas");

        for (JsonElement opcion: opcionesCorrectas){
            pregunta.agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            pregunta.agregarOpcionIncorrecta(opcion.getAsString());
        }

        return pregunta;
    }

    private MultipleChoiceParcial crearMultipleChoiceParcial(JsonObject object) throws PreguntaError {
        String nombrePregunta = object.get("pregunta").toString();
        MultipleChoiceParcial pregunta = new MultipleChoiceParcial(nombrePregunta);

        JsonArray opcionesCorrectas = object.getAsJsonArray("opcionesCorrectas");
        JsonArray opcionesIncorrectas = object.getAsJsonArray("opcionesIncorrectas");

        for (JsonElement opcion: opcionesCorrectas){
            pregunta.agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            pregunta.agregarOpcionIncorrecta(opcion.getAsString());
        }

        return pregunta;
    }

    private MultipleChoiceConPenalidad crearMultipleChoicePenalidad(JsonObject object) throws PreguntaError {
        String nombrePregunta = object.get("pregunta").toString();
        MultipleChoiceConPenalidad pregunta = new MultipleChoiceConPenalidad(nombrePregunta);

        JsonArray opcionesCorrectas = object.getAsJsonArray("opcionesCorrectas");
        JsonArray opcionesIncorrectas = object.getAsJsonArray("opcionesIncorrectas");

        for (JsonElement opcion: opcionesCorrectas){
            pregunta.agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            pregunta.agregarOpcionIncorrecta(opcion.getAsString());
        }

        return pregunta;
    }

    private OrderedChoice crearOrderedChoice(JsonObject object) throws PreguntaError {
        String nombrePregunta = object.get("pregunta").toString();
        OrderedChoice pregunta = new OrderedChoice(nombrePregunta);

        JsonArray orden = object.getAsJsonArray("orden");
        for (JsonElement opcion: orden){
            pregunta.agregarOpcion(opcion.getAsString());
        }

        return pregunta;
    }

    private GroupChoice crearGroupChoice(JsonObject object) throws PreguntaError {
        String nombrePregunta = object.get("pregunta").toString();
        GroupChoice pregunta = new GroupChoice(nombrePregunta);

        JsonArray nombresGrupos = object.getAsJsonArray("grupos");
        JsonArray grupo1 = object.getAsJsonArray("grupo1");
        JsonArray grupo2 = object.getAsJsonArray("grupo2");

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