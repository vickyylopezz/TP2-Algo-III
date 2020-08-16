package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.eventos.preparacion.editorJugadores.ActualizadorVistaJugador;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.CambiarNombreJugadorListener;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.JugadorObservable;
import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.contenedores.CajaVista;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import edu.fiuba.algo3.vista.componentes.textos.SubTitulo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class JugadorVista extends VBox {

    private MiniTexto notifiacion;

    public JugadorVista(JugadorObservable jugador, Integer numeroJugador) {
        jugador.agregarObservador(new ActualizadorVistaJugador(this));

        this.crearEstructura(jugador, numeroJugador);
        this.aplicarEstilo();

        this.desactivarNotificacion();
    }

    public void desactivarNotificacion() { this.notifiacion.setVisible(false); }

    public void activarNorificacion(String mensaje) {
        this.notifiacion.setText(mensaje);
        this.notifiacion.setVisible(true);
    }

    private void crearEstructura(JugadorObservable jugador, Integer numeroJugador) {
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

        TextField inputNombre = new TextField();
        inputNombre.setPromptText("Nombre del jugador ...");
        inputNombre.textProperty().addListener(new CambiarNombreJugadorListener(jugador));

        this.notifiacion = new MiniTexto();
        this.notifiacion.setTextFill(Color.RED);
        this.notifiacion.setStyle("-fx-font-weight: bold;");

        this.getChildren().addAll(
                subTitulo,
                caja,
                inputNombre,
                this.notifiacion
        );
    }

    private void aplicarEstilo() {
        this.setPadding(new Insets(30));
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }
}
