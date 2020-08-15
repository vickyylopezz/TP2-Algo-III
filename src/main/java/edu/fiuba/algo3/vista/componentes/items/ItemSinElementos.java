package edu.fiuba.algo3.vista.componentes.items;

import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import javafx.geometry.Pos;

public class ItemSinElementos extends Item {

    public ItemSinElementos(String titulo) {
        this.getChildren().add(new MiniTexto(titulo));
        this.cargarEstilo();
    }

    public ItemSinElementos() {
        this.getChildren().add(new MiniTexto("Sin Elementos"));
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        String estilo = "-fx-border-width: 0;";
        estilo += "-fx-background-color: transparent;";
        estilo += "-fx-border-style: none;";
        this.setStyle(estilo);
        this.setAlignment(Pos.CENTER);
    }
}
