package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.Tema;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.MediaPlayer;

public class BotonDesmutearVista extends Button {
    public BotonDesmutearVista(MediaPlayer reproductor) {
        this.cargarEstailo();
    }

    private void cargarEstailo() {
        ImageView sonido = new ImageView(CargadorResources.obtenerImagen(Resources.IconoSonidoRuta())
        );
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipal,
                        new CornerRadii(3),
                        Insets.EMPTY
                )
        ));
        this.setGraphic(sonido);
        sonido.setFitWidth(30);
        sonido.setFitHeight(30);
    }
}
