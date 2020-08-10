package edu.fiuba.algo3.vista.escenas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class EstructuraPrincipalVista implements Escena {

    public Scene obtenerEscena() {
        BorderPane raiz = new BorderPane();

        raiz.setTop(this.cabecera());
        raiz.setCenter(this.nucleo());
        //raiz.setCenter(this.centro());

        return new Scene(raiz, 800, 600);
    }

    private Node cabecera() {
        HBox cabecera = new HBox();
        cabecera.setSpacing(10);
        cabecera.setPadding(new Insets(10, 10, 10, 10));
        cabecera.setAlignment(Pos.TOP_CENTER);

        StackPane izq = new StackPane();
        izq.setAlignment(Pos.CENTER_LEFT);
        StackPane cen = new StackPane();
        cen.setAlignment(Pos.CENTER);
        StackPane der = new StackPane();
        der.setAlignment(Pos.CENTER_RIGHT);

        Node contenidoIzq = this.cabeceraIzquierda();
        Node contenidoCen = this.cabeceraCentro();
        Node contenidoDer = this.cabeceraDerecha();

        if (contenidoIzq != null) izq.getChildren().add(contenidoIzq);
        if (contenidoCen != null) cen.getChildren().add(contenidoCen);
        if (contenidoDer != null) der.getChildren().add(contenidoDer);

        cabecera.setHgrow(izq, Priority.ALWAYS);
        cabecera.setHgrow(cen, Priority.ALWAYS);
        cabecera.setHgrow(der, Priority.ALWAYS);

        cabecera.getChildren().addAll(izq, cen, der);

        return cabecera;
    }

    private Node cabeceraCentro() {
        Group contenedor = new Group();

        try {
            FileInputStream stream = new FileInputStream("src/main/resources/logo.png");
            ImageView logo = new ImageView(new Image(stream));

            logo.setPreserveRatio(true);
            logo.setFitHeight(75);

            contenedor.getChildren().add(logo);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        return contenedor;
    }

    abstract protected Node cabeceraDerecha();

    abstract protected Node cabeceraIzquierda();

    abstract protected Node centro();

    private Node nucleo() {
        HBox nucleo = new HBox();
        nucleo.setSpacing(10);
        nucleo.setPadding(new Insets(10, 10, 10, 10));
        nucleo.setAlignment(Pos.CENTER);

        StackPane izq = new StackPane();
        izq.setAlignment(Pos.CENTER_LEFT);
        StackPane cen = new StackPane();
        cen.setAlignment(Pos.CENTER);
        StackPane der = new StackPane();
        der.setAlignment(Pos.CENTER_RIGHT);

        Node contenidoIzq = this.centroIzquierda();
        Node contenidoCen = this.centro();
        Node contenidoDer = this.centroDerecha();

        if (contenidoIzq != null) izq.getChildren().add(contenidoIzq);
        if (contenidoCen != null) cen.getChildren().add(contenidoCen);
        if (contenidoDer != null) der.getChildren().add(contenidoDer);

        nucleo.setHgrow(izq, Priority.ALWAYS);
        nucleo.setHgrow(cen, Priority.ALWAYS);
        nucleo.setHgrow(der, Priority.ALWAYS);

        nucleo.getChildren().addAll(izq, cen, der);

        return nucleo;
    }

    abstract protected Node centroIzquierda();

    abstract protected Node centroDerecha();
}
