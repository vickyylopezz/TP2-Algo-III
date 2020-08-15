package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonDesmutearVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonMutearVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class ContenedorSonido extends HBox {
    protected Node nodo;
    protected MediaPlayer reproductor;

    public ContenedorSonido(MediaPlayer reproductor) {
        this.reproductor = reproductor;
        BotonMutearVista botonMutear = new BotonMutearVista(reproductor);
        BotonDesmutearVista botonDesmutear = new BotonDesmutearVista(reproductor);

        botonMutear.setOnAction(e-> {reproductor.stop();});
        botonDesmutear.setOnAction(e-> {reproductor.play();});

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(botonDesmutear,botonMutear);
        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.nodo = this;

    }

    private void comenzarMusica(){
        this.reproductor.setOnEndOfMedia(new Runnable() {
            public void run() {
                reproductor.seek(Duration.ZERO);
            }
        });
        this.reproductor.play();
    }

    public Node obtenerNodo() {
        return this.nodo;
    }
}
