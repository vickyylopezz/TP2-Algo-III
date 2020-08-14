package edu.fiuba.algo3.vista.componentes.cabeceras;

import edu.fiuba.algo3.vista.componentes.textos.SubTitulo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CabeceraCajaVista extends GridPane {

    private HBox panelIzquierdo;
    private HBox panelDerecho;

    public CabeceraCajaVista() {
        this.estrucutraCabecera();
        this.cargarEstilo();
    }

    public CabeceraCajaVista(String titulo) {
        this.estrucutraCabecera();
        this.cargarEstilo();

        this.agregarAIzquierda(new SubTitulo(titulo));
    }

    private void estrucutraCabecera() {
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();

        col1.setPercentWidth(50);
        col2.setPercentWidth(50);

        this.getColumnConstraints().addAll(col1, col2);

        this.panelIzquierdo = new HBox();
        this.panelDerecho = new HBox();

        this.add(this.panelIzquierdo, 0, 0);
        this.add(this.panelDerecho, 1, 0);
    }

    private void cargarEstilo() {
        String estilo = "-fx-border-width: 0 0 1 0;";
        estilo += "-fx-border-color: #999";
        this.setStyle(estilo);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setPrefHeight(60);
        this.setAlignment(Pos.CENTER);

        this.panelDerecho.setAlignment(Pos.CENTER_RIGHT);
        this.panelIzquierdo.setAlignment(Pos.CENTER_LEFT);
    }

    public void agregarADerecha(Node nodo) {
        if (nodo == null) return;
        this.panelDerecho.getChildren().add(nodo);
    }

    public void agregarAIzquierda(Node nodo) {
        if (nodo == null) return;
        this.panelIzquierdo.getChildren().add(nodo);
    }
}
