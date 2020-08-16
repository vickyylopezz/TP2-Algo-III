package edu.fiuba.algo3.vista.escenas.juego;

import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraCajaVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

public class PreviaPreguntaEscena extends BaseEscena {
    public PreviaPreguntaEscena(MediaPlayer reproductor, Jugada jugada) {
        super(reproductor);
        Node cuerpo = this.crearCuerpo(jugada);
        this.raiz.setCenter(cuerpo);
    }

    private Node crearCuerpo(Jugada jugada) {
        VBox vbox = new VBox(100);
        vbox.setPadding(new Insets(100));
        Label jLabelTipoPregunta = new Label(jugada.obtenerPregunta().mostrarTipoPregunta());
        jLabelTipoPregunta.setAlignment(Pos.CENTER);
        jLabelTipoPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold");

        Label jLabelTituloPregunta = new Label(jugada.tituloPregunta());
        jLabelTituloPregunta.setAlignment(Pos.CENTER);
        jLabelTituloPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold; -fx-background-color: white; -fx-border-color: #9A31E1");
        jLabelTituloPregunta.setPrefHeight(300);
        jLabelTituloPregunta.setPrefWidth(600);

        vbox.getChildren().addAll(jLabelTipoPregunta, jLabelTituloPregunta);
        vbox.setAlignment(Pos.CENTER);


        Label jLabelTurno = new Label("Turno de " + jugada.obtenerJugador().nombre());
        jLabelTurno.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-background-color: white; -fx-border-color: #9A31E1");
        jLabelTurno.setPrefHeight(70);
        jLabelTurno.setPrefWidth(200);

        this.cabecera.definirPanelIzquierdo(jLabelTurno);

        return vbox;
    }
}
