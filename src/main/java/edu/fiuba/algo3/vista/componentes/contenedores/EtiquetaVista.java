package edu.fiuba.algo3.vista.componentes.contenedores;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EtiquetaVista extends StackPane {

    public EtiquetaVista(Node... nodos) {
        super(nodos);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setMaxHeight(40);
        this.setMaxWidth(250);
        this.setPadding(new Insets(5));
        this.setBorder(new Border(new BorderStroke(
                Color.GRAY,
                BorderStrokeStyle.NONE,
                new CornerRadii(2),
                new BorderWidths(1)
        )));
    }
}
