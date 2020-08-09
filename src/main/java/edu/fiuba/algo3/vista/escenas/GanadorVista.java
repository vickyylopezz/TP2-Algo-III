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

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.geometry.Pos.TOP_LEFT;

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
        StackPane etiqueta = new StackPane();
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



        VBox contenedorHorizontal = new VBox(jugador);

        etiqueta.getChildren().add(contenedorHorizontal);
        contenedorHorizontal.setTranslateY(-50);


        return etiqueta;
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
