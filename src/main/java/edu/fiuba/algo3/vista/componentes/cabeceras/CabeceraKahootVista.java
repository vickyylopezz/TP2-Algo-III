package edu.fiuba.algo3.vista.componentes.cabeceras;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CabeceraKahootVista extends GridPane {

    public CabeceraKahootVista(Node nodoIzquierdo, Node nodoDerecho) {
        this.estrucutraCabecera();
        this.cargarEstilo();

        this.contenidoPanelIzquierdo(nodoIzquierdo);
        this.contenidoPanelCentral();
        this.contenidoPanelDerecho(nodoDerecho);
    }

    private void cargarEstilo() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
    }

    private void estrucutraCabecera() {
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        col1.setPercentWidth(30);
        col2.setPercentWidth(40);
        col3.setPercentWidth(30);

        this.getColumnConstraints().addAll(col1, col2, col3);
    }

    private void contenidoPanelIzquierdo(Node nodo) {
        if (nodo == null) return;

        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER_LEFT);
        columna.getChildren().add(nodo);

        this.add(nodo, 0, 0);
    }

    private void contenidoPanelDerecho(Node nodo) {
        if (nodo == null) return;

        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER_RIGHT);
        columna.getChildren().add(nodo);

        this.add(columna, 2, 0);
    }

    private void contenidoPanelCentral() {
        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER);

        Image img = CargadorResources.obtenerImagen(Resources.LogoPrincipalRuta());
        ImageView logo = new ImageView(img);
        logo.setPreserveRatio(true);
        logo.setFitHeight(75);
        columna.getChildren().add(logo);

        this.add(columna, 1, 0);
    }
}
