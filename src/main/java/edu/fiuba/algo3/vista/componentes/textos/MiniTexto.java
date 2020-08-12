package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.text.Font;

public class MiniTexto extends Texto {

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
        this.setFont(new Font(this.getFont().getName(), 16));
    }
}
