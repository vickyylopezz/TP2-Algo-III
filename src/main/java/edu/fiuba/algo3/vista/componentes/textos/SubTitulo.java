package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.text.Font;

public class SubTitulo extends Texto {

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
        this.setFont(new Font(this.getFont().getName(), 18));
    }
}
