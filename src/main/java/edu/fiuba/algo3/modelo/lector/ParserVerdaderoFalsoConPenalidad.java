package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;

public class ParserVerdaderoFalsoConPenalidad implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) {
        String tituloPregunta = objeto.get("pregunta").getAsString();
        boolean respuesta = objeto.get("respuesta").getAsBoolean();

        String opcionCorrecta = "Verdadero";
        String opcionIncorrecta = "Falso";
        if (!respuesta) {
            opcionCorrecta = "Falso";
            opcionIncorrecta = "Verdadero";
        }

        return new VerdaderoFalsoConPenalidad(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }
}
