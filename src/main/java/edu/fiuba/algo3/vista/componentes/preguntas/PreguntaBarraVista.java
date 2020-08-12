package edu.fiuba.algo3.vista.componentes.preguntas;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class PreguntaBarraVista extends HBox {

    private final Pregunta pregunta;

    public PreguntaBarraVista(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setPrefHeight(30);
        this.setAlignment(Pos.CENTER_LEFT);

        MiniTexto titulo = new MiniTexto(this.pregunta.obtenerTitulo());
        this.getChildren().add(titulo);
    }
}
