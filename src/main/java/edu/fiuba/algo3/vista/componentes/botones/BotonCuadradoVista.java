package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.Tema;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class BotonCuadradoVista extends Button {

    public BotonCuadradoVista(String t) {
        super(t);
        this.cargarEstilo();
    }

    public BotonCuadradoVista(ImageView img) {
        this.setGraphic(img);
        img.setFitWidth(25);
        img.setFitHeight(25);
        this.setContentDisplay(ContentDisplay.CENTER);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setBorder(new Border(
                new BorderStroke(
                        Color.TRANSPARENT,
                        BorderStrokeStyle.NONE,
                        new CornerRadii(3),
                        BorderWidths.EMPTY
                )
        ));
        this.setPadding(new Insets(5));
        this.setEffect(new DropShadow(3, Color.LIGHTGREY));
        this.setPrefHeight(50);
        this.activar();
    }

    public void desactivar() {
        this.disableProperty().set(true);
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipalDesactivado,
                        new CornerRadii(3),
                        Insets.EMPTY
                )
        ));
    }

    public void activar() {
        this.disableProperty().set(false);
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipal,
                        new CornerRadii(3),
                        Insets.EMPTY
                )
        ));
    }
}
