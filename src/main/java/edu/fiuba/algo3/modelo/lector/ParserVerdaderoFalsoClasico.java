package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;

public class ParserVerdaderoFalsoClasico implements ParserPregunta {
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

        return new VerdaderoFalsoClasico(tituloPregunta, opcionCorrecta, opcionIncorrecta);
    }
}
