package edu.fiuba.algo3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;

import java.io.*;
import java.util.ArrayList;

public class Lector {
    private ArrayList<Pregunta> preguntas = new ArrayList<>();

    public void extraerPreguntas(File archivo) throws IOException, PreguntaError {
        //File file = new File(getClass().getClassLoader().getResource("Preguntas2.json").getFile());

        JsonReader reader = new JsonReader(new FileReader(archivo));
        JsonElement parser = JsonParser.parseReader(reader);

        JsonArray array = parser.getAsJsonArray();
        for (JsonElement element: array) {
            JsonObject object = element.getAsJsonObject();
            preguntas.add(crearPregunta(object));
        }

        //mostrarContenido(); // DEBUG
    }

    private Pregunta crearPregunta(JsonObject object) throws PreguntaError {
        String titulo = object.get("pregunta").getAsString();
        Pregunta pregunta;

        switch(object.get("tipo").getAsString()) {
            case "VFClasico":
                pregunta = new VerdaderoFalsoClasico(titulo);
                break;

            case "VFPenalidad":
                pregunta = new VerdaderoFalsoConPenalidad(titulo);
                break;

            case "MCClasico":
                pregunta = new MultipleChoiceClasico(titulo);
                break;

            case "MCParcial":
                pregunta = new MultipleChoiceParcial(titulo);
                break;

            case "MCPenalidad":
                pregunta = new MultipleChoiceConPenalidad(titulo);
                break;

            case "Ordered":
                pregunta = new OrderedChoice(titulo);
                break;

            case "Group":
                pregunta = new GroupChoice(titulo);
                break;

            default:
                //System.out.println("No se reconoce el tipo de pregunta");
                //alguna excepcion
                return null;

        }
        pregunta.extraerOpciones(object);
        return pregunta;
    }

    // DEBUG
    private void mostrarContenido(){
        // permite navegar por las preguntas guardadas
        // esto permite tener una lista de preguntas para usar durante el juego
        for (Pregunta pregunta: preguntas) {
            System.out.println("Q: "+pregunta.obtenerTitulo());

            ArrayList<Opcion> opciones = new ArrayList<>();

            if (pregunta.getClass() == GroupChoice.class) {
                ArrayList<Grupo> grupos= ((GroupChoice) pregunta).obtenerGrupos();
                for (Grupo grupo: grupos) {
                    opciones.addAll(grupo.obtenerOpciones());
                }
            } else {
                opciones = pregunta.obtenerOpciones();
            }

            for (Opcion opcion: opciones){
                System.out.println(opcion.obtenerTitulo());
            }
        }
    }

    public ArrayList<Pregunta> obtenerPreguntas(){
        return this.preguntas;
    }

}