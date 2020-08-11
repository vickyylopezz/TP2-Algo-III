package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.componentes.Componente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;


public class BotonCuadradoVista implements Componente {

    private final Button boton;
    private String estilo;
    private Boolean activado;
    private EventHandler<ActionEvent> clickAccion;

    public BotonCuadradoVista(String texto) {
        this.boton = new Button(texto);
        this.cargarEstilo();
        this.activar();
    }

    private void cargarEstilo() {
        this.estilo += "-fx-effect: dropshadow(three-pass-box, grey, 3, 0, 0, 0);";
        this.estilo += "-fx-text-fill: white;";
        this.estilo += "-fx-font-weight: bold;";

        this.boton.setStyle(this.estilo);
        this.boton.setPrefHeight(50);
        this.boton.setBorder(Border.EMPTY);
    }

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
