package edu.fiuba.algo3.vista.componentes.preguntas;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.Componente;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PreguntaBarraVista implements Componente {

    private final Pregunta pregunta;
    private HBox contenedor;

    public PreguntaBarraVista(Pregunta pregunta) {
        this.pregunta = pregunta;

        this.crearVista();
    }

    private void crearVista() {
        this.contenedor = new HBox();
        this.contenedor.setPrefHeight(30);
        this.contenedor.setAlignment(Pos.CENTER_LEFT);

        Label titulo = new Label(this.pregunta.obtenerTitulo());

        this.contenedor.getChildren().add(titulo);
    }

    @Override
    public Node obtenerNodo() {
        return this.contenedor;
    }
}
