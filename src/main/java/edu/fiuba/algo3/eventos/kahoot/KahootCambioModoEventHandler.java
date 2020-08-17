package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.KahootModo;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
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
        try {
            this.modo.iniciar(reproductor);
        } catch (PuntoError puntoError) {
            puntoError.printStackTrace();
        }
    }
}
