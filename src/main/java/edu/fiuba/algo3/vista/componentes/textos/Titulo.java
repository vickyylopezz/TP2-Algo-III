package edu.fiuba.algo3.vista.componentes.textos;

import javafx.scene.Node;
import javafx.scene.text.Font;

public class Titulo extends Texto {

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
        this.setFont(new Font(this.getFont().getName(), 26));
    }
}
