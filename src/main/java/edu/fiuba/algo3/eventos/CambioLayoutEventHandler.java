package edu.fiuba.algo3.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambioLayoutEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private final Scene escena;

    public CambioLayoutEventHandler(Stage stage, Scene escena) {
        this.stage = stage;
        this.escena = escena;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.stage.setScene(this.escena);
        this.stage.show();
    }
}
