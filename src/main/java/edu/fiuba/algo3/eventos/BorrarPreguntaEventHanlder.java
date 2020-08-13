package edu.fiuba.algo3.eventos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class BorrarPreguntaEventHanlder  implements EventHandler<ActionEvent> {

    private final ObservableList<Node> contenedor;
    private final Node itemPregunta;

    public BorrarPreguntaEventHanlder(ObservableList<Node> contenedor, Node itemPregunta) {
        this.contenedor = contenedor;
        this.itemPregunta = itemPregunta;
    }

    @Override
    public void handle(ActionEvent actionEvent) { this.contenedor.remove(this.itemPregunta); }
}