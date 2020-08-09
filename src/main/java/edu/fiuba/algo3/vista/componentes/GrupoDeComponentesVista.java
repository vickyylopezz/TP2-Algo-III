package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.componentes.botones.BotonCircularVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;


public class GrupoDeComponentesVista {

    private final String titulo;
    private Node nodo;

    public GrupoDeComponentesVista(String titulo, Boolean etiquetaIzquierda) {
        this.titulo = titulo;

        this.aplicarEstilo(etiquetaIzquierda);
    }

    private void aplicarEstilo(Boolean etiquetaIzquierda) {
        AnchorPane contenedor = new AnchorPane();
        contenedor.setViewOrder(1);

        contenedor.getChildren().add(this.etiqueta(etiquetaIzquierda));
        contenedor.getChildren().add(this.boton(etiquetaIzquierda));
        contenedor.getChildren().add(this.texto(etiquetaIzquierda));

        this.nodo = contenedor;
    }

    private Node texto(Boolean etiquetaIzquierda) {
        TextField texto = new TextField();
        texto.setPromptText("Ingresar nombre..");

        return texto;
    }

    private Node etiqueta(Boolean etiquetaIzquierda) {
        StackPane etiqueta = new StackPane();

        etiqueta.setMaxHeight(40);
        etiqueta.setMaxWidth(250);
        etiqueta.setPadding(new Insets(5, 5, 5, 5));
        etiqueta.setStyle("-fx-border-radius: 2;-fx-border-color: grey; -fx-background-radius: 2; -fx-background-color: white");

        if (etiquetaIzquierda) {
            etiqueta.setTranslateX(-10);
            etiqueta.setAlignment(Pos.CENTER_LEFT);
        } else {
            etiqueta.setTranslateX(10);
            etiqueta.setAlignment(Pos.CENTER_RIGHT);
        }

        Label texto = new Label(this.titulo);
        etiqueta.getChildren().add(texto);
        etiqueta.setAlignment(Pos.CENTER);

        return etiqueta;
    }

    private Node boton(Boolean etiquetaIzquierda) {
        BotonCircularVista boton;

        if (etiquetaIzquierda) { boton =  BotonCircularVista.crearFlechaSiguiente(); }
        else { boton = BotonCircularVista.crearFlechaAntras(); }

        boton.activar();

        return boton.obtenerNodo();
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
