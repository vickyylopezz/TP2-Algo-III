package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.contenedores.CajaVista;
import edu.fiuba.algo3.vista.componentes.textos.SubTitulo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class JugadorVista extends VBox {

    public JugadorVista(Integer numeroJugador) {
        this.crearEstructura(numeroJugador);
        this.aplicarEstilo();
    }

    private void crearEstructura(Integer numeroJugador) {
        SubTitulo subTitulo = new SubTitulo("JUGADOR " + numeroJugador);
        subTitulo.setStyle("-fx-text-fill: #9463EB");

        CajaVista caja = new CajaVista();
        caja.setPadding(new Insets(20));

        ImageView imagenJugador = new ImageView(
                CargadorResources.obtenerImagen(Resources.IconoJugadorBlancoRuta())
        );
        imagenJugador.setPreserveRatio(true);
        imagenJugador.setFitWidth(100);

        caja.setCenter(imagenJugador);

        TextField inputNombre = new TextField("Ingresar nombre..");

        this.getChildren().addAll(
                subTitulo,
                caja,
                inputNombre
        );
    }

    private void aplicarEstilo() {
        this.setPadding(new Insets(30));
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }
}
