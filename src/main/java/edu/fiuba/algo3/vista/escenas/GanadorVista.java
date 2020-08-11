package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.GanadorControlador;
import edu.fiuba.algo3.controladores.JuegoControlador;
import edu.fiuba.algo3.vista.Resources;
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
    private GanadorControlador controlador;

    public GanadorVista(GanadorControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        BotonEtiquetaVista botonResultados = new BotonEtiquetaVista("Resultados",false);
        botonResultados.obtenerBoton().click((event) -> this.controlador.escenaAnterior());

        return botonResultados.obtenerNodo();
    }

    @Override
    protected Node centro() {
        VBox boxJugador = new VBox();
        boxJugador.setStyle("-fx-border-radius: 2;-fx-border-color: grey; -fx-background-radius: 2; -fx-background-color: white");

        Group contenedorIcono = new Group();
        try {
            FileInputStream stream = new FileInputStream(Resources.IconoJugadorBlancoRuta());
            ImageView icono = new ImageView(new Image(stream));
            icono.setPreserveRatio(true);
            icono.setFitHeight(150);

            contenedorIcono.getChildren().add(icono);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        boxJugador.getChildren().addAll(contenedorIcono);
        boxJugador.setAlignment(Pos.BOTTOM_CENTER);
        boxJugador.setMaxHeight(20);
        boxJugador.setMaxWidth(150);
        boxJugador.setPadding(new Insets(90,50,90,50));

        Label jLabelTituloGanador = new Label("G A N A D O R");
        jLabelTituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");
        jLabelTituloGanador.setAlignment(TOP_CENTER);

        Label jLabelGanador = new Label("nombre ganador");
        jLabelGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");

        VBox vbox = new VBox(20);

        vbox.getChildren().addAll(jLabelTituloGanador,boxJugador,jLabelGanador);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
