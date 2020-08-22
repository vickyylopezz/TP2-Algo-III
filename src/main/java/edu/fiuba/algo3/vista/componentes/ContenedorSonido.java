package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.componentes.botones.BotonDesmutearVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonMutearVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class ContenedorSonido extends HBox {
    public ContenedorSonido(MediaPlayer reproductor) {
        BotonMutearVista botonMutear = new BotonMutearVista(reproductor);
        BotonDesmutearVista botonDesmutear = new BotonDesmutearVista(reproductor);

        botonMutear.setOnAction(e-> reproductor.setMute(true));
        botonDesmutear.setOnAction(e-> reproductor.setMute(false));

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(botonDesmutear,botonMutear);
        this.setAlignment(Pos.BOTTOM_RIGHT);
    }
}
