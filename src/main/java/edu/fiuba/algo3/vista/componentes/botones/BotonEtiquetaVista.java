package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.componentes.Boton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BotonEtiquetaVista implements Boton {

    private Node nodo;
    private String titulo;
    private BotonCircularVista boton;

    public BotonEtiquetaVista(String titulo, Boolean etiquetaIzquierda) {
        this.titulo = titulo;

        this.aplicarEstilo(etiquetaIzquierda);
    }

    private void aplicarEstilo(Boolean etiquetaIzquierda) {
        StackPane contenedor = new StackPane();

        if (etiquetaIzquierda) { contenedor.setAlignment(Pos.CENTER_RIGHT);
        } else { contenedor.setAlignment(Pos.CENTER_LEFT); }

        contenedor.getChildren().add(this.etiqueta(etiquetaIzquierda));
        contenedor.getChildren().add(this.boton(etiquetaIzquierda));

        this.nodo = contenedor;
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

        this.boton = boton;

        return boton.obtenerNodo();
    }

    @Override
    public void desactivar() { this.boton.desactivar(); }

    @Override
    public void activar() { this.boton.activar(); }

    @Override
    public void click(EventHandler<ActionEvent> accion) { this.boton.click(accion); }

    @Override
    public Node obtenerNodo() {
        return this.nodo;
    }

}
