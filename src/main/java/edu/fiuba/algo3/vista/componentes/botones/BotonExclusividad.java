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

public class BotonExclusividad extends Button {
    public BotonExclusividad(){
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        ImageView corona = new ImageView(CargadorResources.obtenerImagen(Resources.IconoCoronaRuta())
        );
        this.setBackground(new Background(
                new BackgroundFill(
                        Tema.colorBotonPrincipal,
                        new CornerRadii(3),
                        Insets.EMPTY
                )
        ));
        this.setGraphic(corona);
        corona.setPreserveRatio(true);
        corona.setFitWidth(50);
        corona.setFitHeight(50);
    }
}
