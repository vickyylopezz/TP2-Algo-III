package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.modelo.juego.Jugada;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class PreviaPreguntaVista {

    private Jugada jugada;
    private Node nodo;

    public PreviaPreguntaVista(Jugada jugada){
        this.jugada = jugada;
        this.aplicarEstilo();
    }

    private void aplicarEstilo() {
        VBox vbox = new VBox(100);
        vbox.setPadding(new Insets(100));
        Label jLabelTipoPregunta = new Label(jugada.obtenerPregunta().obtenerTipo());
        jLabelTipoPregunta.setAlignment(Pos.CENTER);
        jLabelTipoPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold");

        Label jLabelTituloPregunta = new Label(jugada.tituloPregunta());
        jLabelTituloPregunta.setAlignment(Pos.CENTER);
        jLabelTituloPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold; -fx-background-color: white; -fx-border-color: #9A31E1");
        jLabelTituloPregunta.setPrefHeight(300);
        jLabelTituloPregunta.setPrefWidth(600);

        vbox.getChildren().addAll(jLabelTipoPregunta, jLabelTituloPregunta);
        vbox.setAlignment(Pos.CENTER);

        this.nodo = vbox;
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
