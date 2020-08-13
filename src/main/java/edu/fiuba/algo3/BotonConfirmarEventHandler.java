package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonConfirmarEventHandler implements EventHandler<ActionEvent>{
        private Stage stage;
        private Scene proximaEscena;

        public BotonConfirmarEventHandler(Stage stage, Scene proximaEscena) {
            this.stage = stage;
            this.proximaEscena = proximaEscena;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            stage.setScene(proximaEscena);
        }
    }
