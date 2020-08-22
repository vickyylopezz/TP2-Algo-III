package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MiniTexto extends Label {

    public static MiniTexto Negrita(String titulo) {
        MiniTexto texto = new MiniTexto(titulo);
        texto.setFont(Font.font(
                texto.getFont().getName(),
                FontWeight.BOLD,
                texto.getFont().getSize()
        ));
        return texto;
    }

    public MiniTexto() {
        super();
        this.cargarEstilo();
    }

    public MiniTexto(String titulo) {
        super(titulo);
        this.cargarEstilo();
    }

    public MiniTexto(String titulo, Node nodo) {
        super(titulo, nodo);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setFont(new Font("Open Sans", 12));
    }
}
