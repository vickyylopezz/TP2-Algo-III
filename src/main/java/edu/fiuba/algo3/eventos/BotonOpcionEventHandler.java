package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.Opcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonOpcionEventHandler implements EventHandler<ActionEvent> {
    private Opcion opcion;

    public BotonOpcionEventHandler(Opcion opcion) {
        this.opcion = opcion;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.opcion.getValor() == 1) {
            System.out.println(":)");
        } else {
            System.out.println(":(");
        }
    }
}