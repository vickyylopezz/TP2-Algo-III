package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.Kahoot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;

public class KahootReiniciarEventHandler implements EventHandler<ActionEvent> {

    private final Kahoot kahoot;
    private final MediaPlayer reproductor;

    public KahootReiniciarEventHandler(Kahoot kahoot, MediaPlayer reproductor) {
        this.reproductor = reproductor;
        this.kahoot = kahoot;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.reproductor.setMute(true);
        this.reproductor.stop();
        this.kahoot.reiniciar();
    }
}
