package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.KahootModo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;

public class KahootCambioModoEventHandler implements EventHandler<ActionEvent> {

    private KahootModo modo;
    private MediaPlayer reproductor;

    public KahootCambioModoEventHandler(KahootModo modo, MediaPlayer reproductor) { this.modo = modo;
    this.reproductor = reproductor;}

    @Override
    public void handle(ActionEvent actionEvent) {
        this.modo.iniciar(reproductor);
    }
}
