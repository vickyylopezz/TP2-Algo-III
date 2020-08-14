package edu.fiuba.algo3.vista.componentes.cabeceras;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CabeceraKahootVista extends GridPane {

    private StackPane panelIzquierdo;
    private StackPane panelCentral;
    private StackPane panelDerecho;

    public CabeceraKahootVista() {
        this.estrucutraCabecera();
        this.cargarEstilo();
        this.cambiarContenido(this.panelCentral, this.contenidoPanelCentral());
    }

    private void estrucutraCabecera() {
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        col1.setPercentWidth(30);
        col2.setPercentWidth(40);
        col3.setPercentWidth(30);

        this.getColumnConstraints().addAll(col1, col2, col3);

        this.panelIzquierdo = new StackPane();
        this.panelCentral = new StackPane();
        this.panelDerecho = new StackPane();

        this.add(this.panelIzquierdo, 0, 0);
        this.add(this.panelCentral, 1, 0);
        this.add(this.panelDerecho, 2, 0);
    }

    private void cargarEstilo() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        this.panelDerecho.setAlignment(Pos.CENTER_LEFT);
        this.panelCentral.setAlignment(Pos.CENTER);
        this.panelDerecho.setAlignment(Pos.CENTER_RIGHT);
    }

    public void definirPanelIzquierdo(Node nodo) { this.cambiarContenido(this.panelIzquierdo, nodo); }

    public void definirPanelDerecho(Node nodo) { this.cambiarContenido(this.panelDerecho, nodo); }

    private void cambiarContenido(StackPane panel, Node contenido) {
        if (contenido == null) panel.getChildren().clear();
        else panel.getChildren().add(contenido);
    }

    private Node contenidoPanelCentral() {
        Image img = CargadorResources.obtenerImagen(Resources.LogoPrincipalRuta());
        ImageView logo = new ImageView(img);
        logo.setPreserveRatio(true);
        logo.setFitHeight(75);
        return logo;
    }
}
