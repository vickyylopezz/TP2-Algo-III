package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.Resources;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.geometry.Pos.*;

public class GanadorVista{
    protected Node nodo;
    public GanadorVista(String nombre){
        this.aplicarEstilo(nombre);
    }

    public void aplicarEstilo(String nombre){
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

        Label jLabelGanador = new Label(nombre);
        jLabelGanador.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold; -fx-background-color: #8055CB");
        jLabelGanador.setTranslateY(10);
        jLabelGanador.setPrefSize(300,100);
        jLabelGanador.setAlignment(CENTER);

        boxJugador.getChildren().addAll(contenedorIcono,jLabelGanador);
        boxJugador.setSpacing(40);
        boxJugador.setAlignment(BOTTOM_CENTER);
        boxJugador.setMaxHeight(200);
        boxJugador.setMaxWidth(300);
        boxJugador.setPadding(new Insets(70,20,70,20));

        this.nodo = boxJugador;
    }

    public Node obtenerNodo(){
        return this.nodo;
    }
}
