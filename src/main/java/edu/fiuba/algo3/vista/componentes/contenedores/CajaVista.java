package edu.fiuba.algo3.vista.componentes.contenedores;

import javafx.scene.layout.BorderPane;

public class CajaVista extends BorderPane {

    public CajaVista() {
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        String estilo = "-fx-border-radius: 2;";
        estilo += "-fx-background-radius: 2;";
        estilo += "-fx-border-width: 1;";
        estilo += "-fx-border-color: #999;";
        estilo += "-fx-background-color: white;";
        this.setStyle(estilo);
    }
}
