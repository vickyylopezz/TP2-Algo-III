package edu.fiuba.algo3;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Intermission{
    public Intermission(Stage stage, ControladorEscenas controlador){
        Button continuar = new Button("continuar");
        continuar.setOnAction(e -> {
            controlador.crearUnaEscena();
        });
        stage.setScene(new Scene(new VBox(continuar),1080,720));
    }
}
