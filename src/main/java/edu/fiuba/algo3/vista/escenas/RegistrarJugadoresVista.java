package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.GrupoDeComponentesVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.geometry.Pos.TOP_CENTER;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {
    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return null;
    }

    @Override
    protected Node centro() {

        return new GrupoDeComponentesVista("Jugador 2").obtenerNodo();

        /*VBox boxJugador = new VBox();
        boxJugador.setStyle("-fx-border-radius: 1;-fx-border-color: grey; -fx-background-radius: 1; -fx-background-color: white");

        Group contenedorIcono = new Group();
        try {
            FileInputStream stream = new FileInputStream("src/main/resources/jugador.png");
            ImageView icono = new ImageView(new Image(stream));
            icono.setPreserveRatio(true);
            icono.setFitHeight(10);

            contenedorIcono.getChildren().add(icono);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        boxJugador.getChildren().addAll(contenedorIcono);
        boxJugador.setAlignment(Pos.BOTTOM_CENTER);
        boxJugador.setMaxHeight(10);
        boxJugador.setMaxWidth(10);
        boxJugador.setPadding(new Insets(10,10,10,10));

        Label jLabelTituloJugador = new Label("JUGADOR 1");
        jLabelTituloJugador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");
        jLabelTituloJugador.setAlignment(TOP_CENTER);

        Label jLabelGanador = new Label("nombre ganador");
        jLabelGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 30; -fx-font-weight: bold");

        VBox vbox = new VBox(20);

        vbox.getChildren().addAll(jLabelTituloJugador,boxJugador,jLabelGanador);
        vbox.setAlignment(Pos.CENTER);
        return vbox;*/
    }

    @Override
    protected Node centroIzquierda() {
        return new GrupoDeComponentesVista("Jugador 1").obtenerNodo();
    }

    @Override
    protected Node centroDerecha() {
        return new BotonEtiquetaVista("CONFIRMAR",true).obtenerNodo();
    }

}
