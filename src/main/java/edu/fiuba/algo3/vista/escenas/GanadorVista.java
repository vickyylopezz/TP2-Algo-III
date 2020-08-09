package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.geometry.Pos.*;

public class GanadorVista extends EstructuraPrincipalVista{
    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return new BotonEtiquetaVista("Resultados", false).obtenerNodo();
    }

    @Override
    protected Node centro() {
        VBox vbox1 = new VBox();
        vbox1.setStyle("-fx-border-radius: 2;-fx-border-color: grey; -fx-background-radius: 2; -fx-background-color: white");

        /*StackPane etiqueta = new StackPane();
        etiqueta.setMaxHeight(20);
        etiqueta.setMaxWidth(150);
        etiqueta.setPadding(new Insets(90,50,90,50));
        etiqueta.setStyle("-fx-border-radius: 2;-fx-border-color: grey; -fx-background-radius: 2; -fx-background-color: white");

        FileInputStream stream = null;
        try {
            stream = new FileInputStream("src/main/resources/jugador.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView jugador = new ImageView(new Image(stream));
        jugador.setPreserveRatio(true);
        jugador.setFitHeight(150);



        //VBox contenedorHorizontal = new VBox(jugador);

        //etiqueta.getChildren().add(contenedorHorizontal);
        //contenedorHorizontal.setTranslateY(-50);

*/
        Group contenedorIcono1 = new Group();
        try {
            FileInputStream stream = new FileInputStream("src/main/resources/jugador.png");
            ImageView icono1 = new ImageView(new Image(stream));
            icono1.setPreserveRatio(true);
            icono1.setFitHeight(150);

            contenedorIcono1.getChildren().add(icono1);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        vbox1.getChildren().addAll(contenedorIcono1);
        vbox1.setAlignment(Pos.BOTTOM_CENTER);
        vbox1.setMaxHeight(20);
        vbox1.setMaxWidth(150);
        vbox1.setPadding(new Insets(90,50,90,50));

        Label jLabelTituloGanador = new Label("G A N A D O R");
        jLabelTituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");
        jLabelTituloGanador.setAlignment(TOP_CENTER);

        Label jLabelGanador = new Label("nombre ganador");
        jLabelGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");


        VBox vbox = new VBox(20);
        //hbox.setPadding(new Insets(100,50,100,50));
        //vbox.setStyle("-fx-border-radius: 2;-fx-border-color: grey; -fx-background-radius: 2; -fx-background-color: white");

        vbox.getChildren().addAll(jLabelTituloGanador,vbox1,jLabelGanador);
        vbox.setAlignment(Pos.CENTER);
        return vbox;

    }

    @Override
    protected Node centroArriba() {
        return null;
    }

    @Override
    protected Node centroAbajo() {
        return null;
    }
}
