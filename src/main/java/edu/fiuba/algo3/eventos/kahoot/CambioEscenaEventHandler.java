package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambioEscenaEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private final BaseLayout layout;

    public CambioEscenaEventHandler(Stage stage, BaseLayout layout) {
        this.stage = stage;
        this.layout = layout;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.stage.getScene().setRoot(this.layout);
        this.stage.show();
    }
}
