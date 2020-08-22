package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.vista.Tema;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;

public class BienvenidaLayout extends BaseLayout {

    private StackPane cuerpo;

    public BienvenidaLayout() {
        this.cuerpo = new StackPane();
        this.setCenter(this.cuerpo);
    }

    public void eventoBotonPrincipal(EventHandler<ActionEvent> evento) {
        if (evento == null) this.cuerpo.getChildren().clear();

        BotonCuadradoVista boton = new BotonCuadradoVista("Crear Jugadores");
        boton.setPrefSize(300,150);
        boton.setStyle("-fx-font-size: 25px; -fx-font-weight: bolder");
        boton.setTextFill(Tema.ColorBotonSegundarioTexto);
        boton.setBackground(new Background(new BackgroundFill(
                Tema.colorBotonSegundario,
                new CornerRadii(5),
                Insets.EMPTY
        )));

        boton.setOnAction(evento);

        this.cuerpo.getChildren().add(boton);
    }
}
