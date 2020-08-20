package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Texto extends Label {

    public static Texto Negrita(String titulo) {
        Texto texto = new Texto(titulo);
        texto.setFont(Font.font(
                texto.getFont().getName(),
                FontWeight.BOLD,
                texto.getFont().getSize()
        ));
        return texto;
    }

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
        this.setFont(new Font("Open Sans", 16));
    }
}
