package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PuntosObtenidosVista {

    private Node nodo;

    public PuntosObtenidosVista(){

        this.aplicarEstilo();
    }

    private void aplicarEstilo(){
        VBox vbox1 = new VBox(50);

        Group contenedorIcono1 = new Group();
        try {
            FileInputStream stream = new FileInputStream(Resources.IconoJugadorBlancoRuta());
            ImageView icono1 = new ImageView(new Image(stream));
            icono1.setFitHeight(150);

            icono1.setPreserveRatio(true);

            contenedorIcono1.getChildren().add(icono1);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        Label jLabelNombreJugador1 = new Label("Carlos");
        jLabelNombreJugador1.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 30; -fx-font-weight: bold");
        jLabelNombreJugador1.setAlignment(Pos.CENTER);
        Label jLabelPuntos1 = new Label("8");
        jLabelPuntos1.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold");
        jLabelPuntos1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(contenedorIcono1, jLabelNombreJugador1, jLabelPuntos1);
        vbox1.setAlignment(Pos.CENTER);

        VBox vbox2 = new VBox(50);

        Group contenedorIcono2 = new Group();
        try {
            FileInputStream stream = new FileInputStream(Resources.IconoJugadorBlancoRuta());
            ImageView icono2 = new ImageView(new Image(stream));
            icono2.setFitHeight(150);

            icono2.setPreserveRatio(true);

            contenedorIcono2.getChildren().add(icono2);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        Label jLabelNombreJugador2 = new Label("Lucas");
        jLabelNombreJugador2.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 30; -fx-font-weight: bold");
        jLabelNombreJugador2.setAlignment(Pos.CENTER);
        Label jLabelPuntos2 = new Label("4");
        jLabelPuntos2.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold");
        jLabelPuntos2.setAlignment(Pos.CENTER);
        vbox2.getChildren().addAll(contenedorIcono2, jLabelNombreJugador2, jLabelPuntos2);
        vbox2.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(200);
        hbox.getChildren().addAll(vbox1, vbox2);
        hbox.setAlignment(Pos.CENTER);

        this.nodo = hbox;
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
