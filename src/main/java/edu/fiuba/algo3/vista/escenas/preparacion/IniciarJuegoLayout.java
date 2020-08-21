package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;

public class IniciarJuegoLayout extends BaseLayout {

    private StackPane cuerpo;

    public IniciarJuegoLayout() {
        this.cuerpo = new StackPane();
        this.setCenter(this.cuerpo);
    }

    public void eventoBotonPrincipal(EventHandler<ActionEvent> evento) {
        if (evento == null) this.cuerpo.getChildren().clear();

        String tituloBoton = "Iniciar Juego";
        BotonCuadradoVista boton = new BotonCuadradoVista(tituloBoton);
        boton.setOnAction(evento);

        this.cuerpo.getChildren().add(boton);
    }
}
