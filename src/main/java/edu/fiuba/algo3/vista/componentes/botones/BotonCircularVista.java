package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.Componente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BotonCircularVista implements Componente {

    private Button boton;
    private String estilo;

    private Boolean activado;
    private EventHandler<ActionEvent> clickAccion;

    static public BotonCircularVista crearConTexto(String texto) {
        BotonCircularVista boton = new BotonCircularVista();
        boton.definirTexto(texto);
        return boton;
    }

    static public BotonCircularVista crearFlechaSiguiente() {
        return BotonCircularVista.crearConImagen(Resources.IconoFlechaDerechaRuta());
    }

    static public BotonCircularVista crearFlechaAntras() {
        return BotonCircularVista.crearConImagen(Resources.IconoFlechaIzquierdaRuta());
    }

    static public BotonCircularVista crearConImagen(String path) {
        BotonCircularVista boton = new BotonCircularVista();
        boton.definirImagen(path);
        return boton;
    }

    public BotonCircularVista() {
        this.boton = new Button();
        this.cargarEstilo();

        this.activar();
    }

    private void cargarEstilo() {
        this.estilo = "-fx-background-radius: 50%;";
        this.estilo += "-fx-border-radius: 50%;";
        this.estilo += "-fx-effect: dropshadow(three-pass-box, grey, 3, 0, 0, 0);";

        this.boton.setStyle(this.estilo);
        this.boton.setPrefHeight(50);
        this.boton.setPrefWidth(50);
        this.boton.setBorder(Border.EMPTY);
    }

    public void definirTexto(String texto) { this.boton.setText(texto); }

    public void definirImagen(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            ImageView view = new ImageView(new Image(stream));
            view.setPreserveRatio(true);
            view.setFitHeight(25);
            view.setFitWidth(25);
            this.boton.setGraphic(view);
            this.boton.setContentDisplay(ContentDisplay.CENTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public void desactivar() {
        this.boton.setStyle(this.estilo + "-fx-background-color: #7D7D7D;");
    }

    public void activar() {
        this.boton.setStyle(this.estilo + "-fx-background-color: #9463EB;");
    }
*/
    public void desactivar() {
        this.activado = false;
        this.boton.setStyle(this.estilo + "-fx-background-color: #7D7D7D;");
        this.boton.setOnAction(null);
    }

    public void activar() {
        this.activado = true;
        this.boton.setStyle(this.estilo + "-fx-background-color: #9463EB;");
        this.boton.setOnAction(this.clickAccion);
    }

    public void click(EventHandler<ActionEvent> accion) {
        if (this.activado) this.boton.setOnAction(accion);
        this.clickAccion = accion;
    }

    @Override
    public Node obtenerNodo() {
        return this.boton;
    }
}
