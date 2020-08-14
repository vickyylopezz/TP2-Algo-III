package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonDesmutearVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonMutearVista;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class ContenedorSonido extends VBox{
    protected Node nodo;
    protected MediaPlayer reproductor;
    protected Media musica;

    public ContenedorSonido(MediaPlayer reproductor) {
        this.reproductor = reproductor;
        BotonMutearVista botonMutear = new BotonMutearVista(reproductor);
        BotonDesmutearVista botonDesmutear = new BotonDesmutearVista(reproductor);

        botonMutear.setOnAction(e-> {reproductor.stop();botonMutear.setDisable(true);botonDesmutear.setDisable(false);});
        botonDesmutear.setOnAction(e-> {reproductor.play();botonMutear.setDisable(false);botonDesmutear.setDisable(true);});

        if(reproductor.isMute()){
            comenzarMusica();
            botonMutear.setDisable(true);
            botonDesmutear.setDisable(false);
        }else{
            botonMutear.setDisable(false);
            botonDesmutear.setDisable(true);
        }

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(botonDesmutear,botonMutear);
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
