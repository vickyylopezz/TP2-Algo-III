package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.kahoot.KahootModo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class KahootCambioModoEventHandler implements EventHandler<ActionEvent> {

    private KahootModo modo;

    public KahootCambioModoEventHandler(KahootModo modo) { this.modo = modo; }

    @Override
    public void handle(ActionEvent actionEvent) { this.modo.iniciar(); }
}
