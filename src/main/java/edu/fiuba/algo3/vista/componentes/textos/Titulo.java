package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Titulo extends Label {

    public static Titulo Negrita(String titulo) {
        Titulo texto = new Titulo(titulo);
        texto.setFont(Font.font(
                texto.getFont().getName(),
                FontWeight.BOLD,
                texto.getFont().getSize()
        ));
        return texto;
    }

    public Titulo() {
        super();
        this.cargarEstilo();
    }

    public Titulo(String titulo) {
        super(titulo);
        this.cargarEstilo();
    }

    public Titulo(String titulo, Node nodo) {
        super(titulo, nodo);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setFont(new Font("Open Sans", 24));
    }
}
