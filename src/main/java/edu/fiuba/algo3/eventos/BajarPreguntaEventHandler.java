package edu.fiuba.algo3.eventos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class BajarPreguntaEventHandler implements EventHandler<ActionEvent> {

    private final ObservableList<Node> contenedor;
    private final Node itemPregunta;

    public BajarPreguntaEventHandler(ObservableList<Node> contenedor, Node itemPregunta) {
        this.contenedor = contenedor;
        this.itemPregunta = itemPregunta;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        int indiceAMover = this.contenedor.indexOf(this.itemPregunta) + 1;
        if (indiceAMover == this.contenedor.size()) return;
        this.contenedor.remove(this.itemPregunta);
        this.contenedor.add(indiceAMover, this.itemPregunta);
    }
}
