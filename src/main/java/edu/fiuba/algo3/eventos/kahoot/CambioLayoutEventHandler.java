package edu.fiuba.algo3.eventos.kahoot;

import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CambioLayoutEventHandler implements EventHandler<ActionEvent> {

    private final Pane panelPadre;
    private final BaseLayout layout;

    public CambioLayoutEventHandler(Pane panelPadre, BaseLayout layout) {
        this.panelPadre = panelPadre;
        this.layout = layout;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.panelPadre.getChildren().clear();
        this.panelPadre.getChildren().add(this.layout);
    }
}
