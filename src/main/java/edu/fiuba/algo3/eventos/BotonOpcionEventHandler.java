package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonOpcionEventHandler implements EventHandler<ActionEvent> {
    private Opcion opcion;

    public BotonOpcionEventHandler(Opcion opcion) {
        this.opcion = opcion;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.opcion.obtenerPunto().obtenerValor() == 1) {
            System.out.println(":)");
        } else {
            System.out.println(":(");
        }
    }
}