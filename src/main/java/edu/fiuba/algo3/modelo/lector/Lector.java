package edu.fiuba.algo3.modelo.lector;

import com.google.gson.*;

import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorSintaxisError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.parsers.ParserGroupChoice;
import edu.fiuba.algo3.modelo.lector.parsers.ParserMultipleChoice;
import edu.fiuba.algo3.modelo.lector.parsers.ParserOrderedChoice;
import edu.fiuba.algo3.modelo.lector.parsers.ParserVerdaderoFalso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Lector {

    public ArrayList<Pregunta> extraerPreguntas(File archivo) throws LectorError, PreguntaError {
        JsonElement preguntasJson;
        try {
            JsonReader reader = new JsonReader(new FileReader(archivo));
            preguntasJson = JsonParser.parseReader(reader);
        }
        catch (FileNotFoundException e) {
            throw new LectorError("Error al leer el archivo");
        } catch (JsonSyntaxException e) {
            throw new LectorSintaxisError(e.toString());
        }

        return this.extraerPreguntas(preguntasJson);
    }

    public ArrayList<Pregunta> extraerPreguntas(String cadenaJson) throws LectorError, PreguntaError {
        if (cadenaJson == null) return null;

        JsonElement preguntasJson;
        try { preguntasJson = JsonParser.parseString(cadenaJson); }
        catch (JsonSyntaxException e) { throw new LectorSintaxisError(e.toString()); }

        return this.extraerPreguntas(preguntasJson);
    }

    public ArrayList<Pregunta> extraerPreguntas(JsonElement preguntasJson) throws LectorFormatoDePreguntaError, PreguntaError {
        ArrayList<Pregunta> preguntas = new ArrayList<>();

        JsonArray preguntasArray = preguntasJson.getAsJsonArray();
        for (JsonElement preguntaElement: preguntasArray) {
            JsonObject preguntaObjeto;
            try { preguntaObjeto = preguntaElement.getAsJsonObject(); }
            catch (Exception e) {
                throw new LectorFormatoDePreguntaError("Pregunta con formato invalido");
            }

            preguntas.add(this.clasificadorPregunta(preguntaObjeto).parsear(preguntaObjeto));
        }

        return preguntas;
    }

    public Pregunta extraerPregunta(String cadenaJson) throws LectorError, PreguntaError {
        if (cadenaJson == null) return null;

        JsonElement preguntaJson;
        try { preguntaJson = JsonParser.parseString(cadenaJson); }
        catch (JsonSyntaxException e) {
            throw new LectorSintaxisError(e.toString());
        }
        JsonObject preguntaObjeto = preguntaJson.getAsJsonObject();

        return this.clasificadorPregunta(preguntaObjeto).parsear(preguntaObjeto);
    }

    private ParserPregunta clasificadorPregunta(JsonObject preguntaObjeto) throws LectorFormatoDePreguntaError {
        JsonElement tipoJson = preguntaObjeto.get("tipo");
        if (tipoJson == null) throw new LectorFormatoDePreguntaError("Tipo de pregunta invalido");

        String tipoPregunta = tipoJson.getAsString();
        switch (tipoPregunta) {
            case "Ordered": return new ParserOrderedChoice();
            case "Group": return new ParserGroupChoice();
            case "VFClasico":
            case "VFPenalidad":
                return new ParserVerdaderoFalso();
            case "MCClasico":
            case "MCParcial":
            case "MCPenalidad":
                return new ParserMultipleChoice();
        }

        throw new LectorFormatoDePreguntaError("tipo de objeto " + tipoPregunta + " invalido");
    }
}