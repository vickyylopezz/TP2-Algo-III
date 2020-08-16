package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonConfirmarEventHandler implements EventHandler<ActionEvent>{
    private ControladorEscenas controlador;
    private Stage stage;

    public BotonConfirmarEventHandler(ControladorEscenas controlador) {
        this.controlador = controlador;
        this.stage = controlador.getStage();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // OJO: en la ultima pregunta, solo esta jugando el jugador 1
        if (controlador.getIterador().hasNext()) {
            controlador.actualizarAtributos();
            new Intermission(controlador);
        } else {
            System.exit(0);
        }
    }
}
