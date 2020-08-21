package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.kahoot.Kahoot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class KahootSalirEventHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    private MediaPlayer reproductor;
    public KahootSalirEventHandler(Stage stage, MediaPlayer reproductor) {
        this.reproductor = reproductor;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.reproductor.stop();
        Kahoot app = new Kahoot(stage);
        app.iniciar();
    }
}
