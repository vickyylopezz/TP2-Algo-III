package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public abstract class Texto extends Label {

    public Texto() {
        super();
        this.cargarFuente();
    }

    public Texto(String titulo) {
        super(titulo);
        this.cargarFuente();
    }

    public Texto(String titulo, Node nodo) {
        super(titulo, nodo);
        this.cargarFuente();
    }

    private void cargarFuente() {
        this.setFont(new Font(
                "Open Sans",
                12
        ));
    }
}
