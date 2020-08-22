package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.Kahoot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;

public class KahootReiniciarEventHandler implements EventHandler<ActionEvent> {

    private final Kahoot kahoot;

    public KahootReiniciarEventHandler(Kahoot kahoot) {
        this.kahoot = kahoot;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.kahoot.reiniciar();
    }
}
