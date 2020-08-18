package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;

public class ParserVerdaderoFalsoClasico implements ParserPregunta {

    /* Posible refactor unificar clases ParserVerdadoFalsoClasico con
    *  ParserVerdaderoFalsoConPenalidad.
    * */

    @Override
    public Pregunta parsear(JsonObject objeto) throws LectorFormatoDePreguntaError {
        JsonElement tipoJson = objeto.get("tipo");
        if (tipoJson == null) {
            throw new LectorFormatoDePreguntaError("Tipo de pregunta no definido");
        }
        String tipo = tipoJson.getAsString();
        if (!tipo.equals("VFClasico")) {
            throw new LectorFormatoDePreguntaError("Tipo pregunta invalido");
        }

        JsonElement preguntaJson = objeto.get("pregunta");
        if (preguntaJson == null) {
            throw new LectorFormatoDePreguntaError("Falta atributo pregunta");
        }
        String tituloPregunta = preguntaJson.getAsString();

        JsonElement opcionJson = objeto.get("respuesta");
        if (opcionJson == null) {
            throw new LectorFormatoDePreguntaError("Falta atributo respuesta");
        }
        boolean repuesta = opcionJson.getAsBoolean();

        String opcionCorrecta = "Verdadero";
        String opcionIncorrecta = "Falso";
        if (!repuesta) {
            opcionCorrecta = "Falso";
            opcionIncorrecta = "Verdadero";
        }

        return new VerdaderoFalsoClasico(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }
}
