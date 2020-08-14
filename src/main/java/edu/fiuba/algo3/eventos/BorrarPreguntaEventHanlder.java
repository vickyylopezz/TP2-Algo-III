package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class BorrarPreguntaEventHanlder  implements EventHandler<ActionEvent> {

    private final ObservableList<Pregunta> preguntas;
    private final Pregunta pregunta;

    public BorrarPreguntaEventHanlder(ObservableList<Pregunta> preguntas, Pregunta pregunta) {
        this.preguntas = preguntas;
        this.pregunta = pregunta;
    }

    @Override
    public void handle(ActionEvent actionEvent) { this.preguntas.remove(this.pregunta); }
}