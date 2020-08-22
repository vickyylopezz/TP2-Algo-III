package edu.fiuba.algo3.vista.componentes.contenedores;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class CajaVista extends BorderPane {

    public CajaVista() {
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        String estilo = "-fx-border-radius: 5;";
        estilo += "-fx-background-radius: 50;";
        estilo += "-fx-border-width: 0;";
        estilo += "-fx-background-color: white;";
        this.setStyle(estilo);
        this.setEffect(new DropShadow(5, Color.GREY));
    }
}
