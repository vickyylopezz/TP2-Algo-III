package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JugadorVista {

    private final String titulo;
    private Node nodo;
    private TextField texto;
    private Label etiqueta = new Label();

    public JugadorVista(String titulo) {
        this.titulo = titulo;

        this.aplicarEstilo();
    }

    private void aplicarEstilo() {
        VBox contenedor = new VBox();
        contenedor.setPadding(new Insets(30));
        contenedor.setSpacing(20);
        contenedor.setAlignment(Pos.CENTER);

        contenedor.getChildren().addAll(
                this.etiqueta(),
                this.texto()
        );

        this.nodo = contenedor;
    }

    private Node texto() {
        this.texto = new TextField();
        texto.setPromptText("Ingresar nombre..");
        texto.setStyle("-fx-font-size: 18");

        this.etiqueta = new Label();
        etiqueta.setStyle("-fx-font-size: 14");

        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(texto, etiqueta);
        vbox.setAlignment(Pos.CENTER_LEFT);

        return vbox;
    }

    public boolean esInvalido() {
        return (this.texto.getText().trim().equals(""));
    }

    public TextField obtenerTexto(){
        return this.texto;
    }

    private Node etiqueta() {
        VBox boxJugador = new VBox();
        String estilo = "-fx-border-radius: 2;";
        estilo += "-fx-border-color: grey;";
        estilo += "-fx-background-radius: 2;";
        estilo += "-fx-background-color: white;";
        boxJugador.setStyle(estilo);

        Group contenedorIcono = new Group();
        try {
            FileInputStream stream = new FileInputStream(Resources.IconoJugadorBlancoRuta());
            ImageView icono = new ImageView(new Image(stream));
            icono.setPreserveRatio(true);
            icono.setFitHeight(150);

            contenedorIcono.getChildren().add(icono);
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        boxJugador.getChildren().add(contenedorIcono);
        boxJugador.setAlignment(Pos.BOTTOM_CENTER);
        boxJugador.setMaxHeight(60);
        boxJugador.setMaxWidth(90);
        boxJugador.setPadding(new Insets(10));

        Label texto = new Label(this.titulo);
        texto.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 25; -fx-font-weight: bold");

        VBox vbox = new VBox(10);

        vbox.getChildren().addAll(texto, boxJugador);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public Node obtenerNodo() {
        return this.nodo;
    }

    public void validarJugadorVista() {
        this.etiqueta.setText("Debe ingresar un nombre");
        this.etiqueta.setTextFill(Color.web("#FF0000"));
        this.obtenerTexto().requestFocus();
    }

    public void confirmarJugadorVista() {
        this.etiqueta.setText("");
    }
}
