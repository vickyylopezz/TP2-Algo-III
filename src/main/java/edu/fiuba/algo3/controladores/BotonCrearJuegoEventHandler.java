package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonCrearJuegoEventHandler implements EventHandler<ActionEvent> {

    private Stage escenario;
    private Scene siguienteEscena = null;

    public void BotonCrearEventHandler(Stage escenario, Scene escena) {
        this.escenario = escenario;
        this.siguienteEscena = escena;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        this.escenario.setScene(this.siguienteEscena);
        this.escenario.show();
    }
}
