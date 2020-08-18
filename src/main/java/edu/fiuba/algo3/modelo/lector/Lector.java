package edu.fiuba.algo3.modelo.lector;

import com.google.gson.*;

import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorSintaxisError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;

public class Lector {

    public Pregunta extraerPregunta(String cadenaJson) throws LectorError, PreguntaError {
        if (cadenaJson == null) return null;

        JsonElement preguntaJson;
        try { preguntaJson = JsonParser.parseString(cadenaJson); }
        catch (JsonSyntaxException e) {
            throw new LectorSintaxisError(e.toString());
        }
        JsonObject preguntaObjeto = preguntaJson.getAsJsonObject();

        ParserPregunta parserPregunta = this.clasificadorPregunta(preguntaObjeto);

        return parserPregunta.parsear(preguntaObjeto);
    }

    private ParserPregunta clasificadorPregunta(JsonObject preguntaObjeto) throws LectorFormatoDePreguntaError {
        JsonElement tipoJson = preguntaObjeto.get("tipo");
        if (tipoJson == null) throw new LectorFormatoDePreguntaError("Tipo de pregunta invalido");

        String tipoPregunta = tipoJson.getAsString();
        switch (tipoPregunta) {
            case "VFClasico": return new ParserVerdaderoFalsoClasico();
            case "VFPenalidad": return new ParserVerdaderoFalsoConPenalidad();
            case "MCClasico": return new ParserMultipleChoiceClasico();
            case "MCParcial": return new ParserMultipleChoiceParcial();
            case "MCPenalidad": return new ParserMultipleChoiceConPenalidad();
            case "Ordered": return new ParserOrderedChoice();
            case "Group": return new ParserGroupChoice();
        }

        throw new LectorFormatoDePreguntaError("tipo de objeto " + tipoPregunta + " invalido");
    }
}