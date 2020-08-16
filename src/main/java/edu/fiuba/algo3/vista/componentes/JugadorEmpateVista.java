package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.util.punto.Punto;
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

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.CENTER;

public class JugadorEmpateVista {
    protected Node nodo;
    public JugadorEmpateVista(Jugador jugador) throws PuntoError {
        this.cargarEstilo(jugador.nombre(),jugador.puntajeTotal());
    }

    private void cargarEstilo(String nombre, Punto puntaje) throws PuntoError {
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

        Label jLabelNombre = new Label(nombre);
        jLabelNombre.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold; -fx-background-color: #8055CB");
        jLabelNombre.setTranslateY(10);
        jLabelNombre.setPrefSize(300,100);
        jLabelNombre.setAlignment(CENTER);

        Label jLabelPuntos = new Label(puntaje.obtenerPunto().obtenerValor().toString());
        jLabelPuntos.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold; -fx-background-color: #8055CB");
        jLabelPuntos.setTranslateY(10);
        jLabelPuntos.setPrefSize(100,100);
        jLabelPuntos.setAlignment(CENTER);

        boxJugador.getChildren().addAll(contenedorIcono,jLabelPuntos,jLabelNombre);
        boxJugador.setSpacing(40);
        boxJugador.setAlignment(CENTER);
        boxJugador.setMaxHeight(200);
        boxJugador.setMaxWidth(300);
        boxJugador.setPadding(new Insets(50,20,50,20));

        this.nodo = boxJugador;
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
