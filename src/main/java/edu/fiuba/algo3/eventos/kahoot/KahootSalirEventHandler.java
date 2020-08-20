package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.Kahoot;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class KahootSalirEventHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    public KahootSalirEventHandler(Stage stage) {
       this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Kahoot app = new Kahoot(stage);
        try {
            app.iniciar();
        } catch (PuntoError puntoError) {
            puntoError.printStackTrace();
        }
    }
}
