package edu.fiuba.algo3.vista.componentes.cabeceras;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.Componente;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CabeceraKahootVista implements Componente {

    private GridPane cabecera;

    public CabeceraKahootVista(Node nodoIzquierdo, Node nodoDerecho) {
        this.estrucutraCabecera();

        this.contenidoPanelIzquierdo(nodoIzquierdo);
        this.contenidoPanelCentral();
        this.contenidoPanelDerecho(nodoDerecho);
    }

    private void estrucutraCabecera() {
        this.cabecera = new GridPane();
        this.cabecera.setPadding(new Insets(10, 10, 10, 10));
        this.cabecera.setAlignment(Pos.CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        col1.setPercentWidth(30);
        col2.setPercentWidth(40);
        col3.setPercentWidth(30);

        this.cabecera.getColumnConstraints().addAll(col1, col2, col3);
    }

    private void contenidoPanelIzquierdo(Node nodo) {
        if (nodo == null) return;

        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER_LEFT);

        columna.getChildren().add(nodo);

        this.cabecera.add(columna, 0, 0);
    }

    private void contenidoPanelDerecho(Node nodo) {
        if (nodo == null) return;

        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER_RIGHT);

        columna.getChildren().add(nodo);

        this.cabecera.add(columna, 2, 0);
    }

    private void contenidoPanelCentral() {
        FileInputStream stream = null;
        try { stream = new FileInputStream(Resources.LogoPrincipalRuta()); }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        if (stream == null) return;

        StackPane columna = new StackPane();
        columna.setAlignment(Pos.CENTER);

        ImageView logo = new ImageView(new Image(stream));
        logo.setPreserveRatio(true);
        logo.setFitHeight(75);

        columna.getChildren().add(logo);

        this.cabecera.add(columna, 1, 0);
    }

    @Override
    public Node obtenerNodo() { return this.cabecera; }
}
