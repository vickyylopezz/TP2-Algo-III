package edu.fiuba.algo3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.modelo.*;

import java.io.*;
import java.util.ArrayList;

public class Lector {
    private ArrayList<Pregunta> preguntas = new ArrayList<>();

    public void extraerPreguntas() throws IOException, PreguntaError {
        File file = new File(getClass().getClassLoader().getResource("Preguntas2.json").getFile());

        JsonReader reader = new JsonReader(new FileReader(file));
        JsonElement parser = JsonParser.parseReader(reader);

        JsonArray array = parser.getAsJsonArray();
        for (JsonElement element: array) {
            JsonObject object = element.getAsJsonObject();
            preguntas.add(crearPregunta(object));
        }

        mostrarContenido(); // DEBUG
    }

    private Pregunta crearPregunta(JsonObject object) throws PreguntaError {
        // Faltan los casos para las preguntas MCPenalidad, Ordered y Group
        String titulo = object.get("pregunta").getAsString();
        Pregunta pregunta;

        switch(object.get("tipo").getAsString()) {
            case "VFClasico":
                pregunta = new VerdaderoFalsoClasico(titulo, 0);
                break;

            case "VFPenalidad":
                pregunta = new VerdaderoFalsoConPenalidad(titulo, true);
                break;

            case "MCClasico":
                pregunta = new MultipleChoiceClasico(titulo, 0);
                //System.out.println(object.get("opcionesCorrectas"));
                break;

            case "MCParcial":
                pregunta = new MultipleChoiceParcial(titulo, 0);
                break;

            case "MCPenalidad":
                pregunta = new MultipleChoiceParcial(titulo, 0);
                //pregunta = new MultipleChoicePenalidad(titulo, 0);
                //return null;
                break;

            case "Ordered":
                pregunta = new MultipleChoiceParcial(titulo, 0);
                //pregunta = new OrderedChoice(titulo, 0);
                //return null;
                break;

            case "Group":
                pregunta = new MultipleChoiceParcial(titulo, 0);
                //pregunta = new GroupChoice(titulo, 0);
                //return null;
                break;

            default:
                throw new PreguntaError("No se reconoce el tipo de pregunta");

        }
        pregunta.extraerOpciones(object);
        return pregunta;
    }

    // DEBUG
    private void mostrarContenido(){
        for (Pregunta pregunta: preguntas) {
            System.out.println("Q: "+pregunta.obtenerTitulo());
            ArrayList<Opcion> opciones= pregunta.obtenerOpciones();
            for (Opcion opcion: opciones){
                System.out.println(opcion.getTitulo());
            }
        }
    }

    public ArrayList<Pregunta> obtenerPreguntas(){
        return this.preguntas;
    }

}