package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class SubirPreguntaEventHandler implements EventHandler<ActionEvent> {

    private final ObservableList<Pregunta> preguntas;
    private final Pregunta pregunta;

    public SubirPreguntaEventHandler(ObservableList<Pregunta> preguntas, Pregunta pregunta) {
        this.preguntas = preguntas;
        this.pregunta = pregunta;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        int indicePregunta = this.preguntas.indexOf(this.pregunta);
        int indiceAMover = indicePregunta - 1;

        if (indiceAMover < 0) return;

        this.preguntas.remove(indicePregunta);
        this.preguntas.add(indiceAMover, this.pregunta);
    }
}
