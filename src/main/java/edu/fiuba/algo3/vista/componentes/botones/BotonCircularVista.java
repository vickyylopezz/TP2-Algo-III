package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.Tema;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BotonCircularVista extends Button {

    public BotonCircularVista(String t) {
        super(t);
        this.cargarEstilo();
    }

    public BotonCircularVista(ImageView img) {
        img.setPreserveRatio(true);
        img.setFitWidth(25);
        img.setFitHeight(25);
        this.setGraphic(img);
        this.setContentDisplay(ContentDisplay.CENTER);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setEffect(new DropShadow(5, Color.GREY));
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setTextFill(Tema.ColorBotonPrincipalTexto);
        this.setBorder(new Border(
                new BorderStroke(
                        Color.TRANSPARENT,
                        BorderStrokeStyle.NONE,
                        new CornerRadii(50, true),
                        BorderWidths.EMPTY
                )
        ));
        this.activar();
    }

    public void desactivar() {
        this.disableProperty().set(true);
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipalDesactivado,
                        new CornerRadii(50, true),
                        Insets.EMPTY
                )
        ));
    }

    public void activar() {
        this.disableProperty().set(false);
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipal,
                        new CornerRadii(50, true),
                        Insets.EMPTY
                )
        ));
    }
}
