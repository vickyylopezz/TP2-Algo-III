package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SubTitulo extends Label {

    public static SubTitulo Negrita(String titulo) {
        SubTitulo texto = new SubTitulo(titulo);
        texto.setFont(Font.font(
                texto.getFont().getName(),
                FontWeight.BOLD,
                texto.getFont().getSize()
        ));
        return texto;
    }

    public SubTitulo() {
        super();
        this.cargarEstilo();
    }

    public SubTitulo(String titulo) {
        super(titulo);
        this.cargarEstilo();
    }

    public SubTitulo(String titulo, Node nodo) {
        super(titulo, nodo);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setFont(new Font("Open Sans", 20));
    }
}
