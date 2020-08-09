package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
        HBox contenedor = new HBox(200);
        contenedor.setPadding(new Insets(50));
        contenedor.setAlignment(Pos.CENTER);

        StackPane izq = new StackPane();
        izq.setAlignment(Pos.CENTER_LEFT);
        StackPane der = new StackPane();
        der.setAlignment(Pos.CENTER_RIGHT);

        BotonEtiquetaVista boton = new BotonEtiquetaVista(this.titulo,etiquetaIzquierda);

        izq.getChildren().add(this.texto());
        der.getChildren().add(boton.obtenerNodo());

        contenedor.getChildren().addAll(izq, der);

        contenedor.setHgrow(izq,Priority.ALWAYS);
        contenedor.setHgrow(der,Priority.ALWAYS);

        this.nodo = contenedor;
    }

    private Node texto() {
        TextField texto = new TextField();
        texto.setPromptText("Ingresar nombre..");
        texto.setAlignment(Pos.CENTER_LEFT);

        return texto;
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
