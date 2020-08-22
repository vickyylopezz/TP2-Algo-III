package edu.fiuba.algo3.modelo.lector.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.ParserPregunta;
import edu.fiuba.algo3.modelo.preguntas.VerdaderoFalso;

public class ParserVerdaderoFalso implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws LectorFormatoDePreguntaError {
        JsonElement tipoJson = objeto.get("tipo");
        if (tipoJson == null) {
            throw new LectorFormatoDePreguntaError("Tipo de pregunta no definido");
        }
        String tipo = tipoJson.getAsString();
        if (!tipo.equals("VFClasico") && !tipo.equals("VFPenalidad")) {
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
        String respuesta = opcionJson.getAsString();

        String opcionCorrecta = "Verdadero";
        String opcionIncorrecta = "Falso";
        if (respuesta.equals("Falso")) {
            opcionCorrecta = "Falso";
            opcionIncorrecta = "Verdadero";
        }

        if (tipo.equals("VFClasico")) {
            return VerdaderoFalso.Clasico(tituloPregunta, opcionCorrecta, opcionIncorrecta);
        }
        return VerdaderoFalso.ConPenalidad(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }
}
