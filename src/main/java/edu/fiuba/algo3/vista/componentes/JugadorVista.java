package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.eventos.preparacion.editorJugadores.ActualizadorVistaJugador;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.CambiarNombreJugadorListener;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.JugadorObservable;
import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.contenedores.CajaVista;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import edu.fiuba.algo3.vista.componentes.textos.SubTitulo;
import edu.fiuba.algo3.vista.componentes.textos.Texto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class JugadorVista extends CajaVista {

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
        Texto titulo = Texto.Negrita("JUGADOR " + numeroJugador);
        titulo.setStyle("-fx-text-fill: #9463EB");

        ImageView imagenJugador = new ImageView(
                CargadorResources.obtenerImagen(Resources.IconoJugadorBlancoRuta())
        );
        imagenJugador.setPreserveRatio(true);
        imagenJugador.setFitWidth(100);
        Group contenedorImagen = new Group(imagenJugador);

        TextField inputNombre = new TextField();
        inputNombre.setPromptText("Nombre del jugador ...");
        inputNombre.textProperty().addListener(new CambiarNombreJugadorListener(jugador));

        this.notifiacion = new MiniTexto();
        this.notifiacion.setTextFill(Color.RED);
        this.notifiacion.setStyle("-fx-font-weight: bold;");

        VBox contenedor = new VBox(titulo, contenedorImagen, inputNombre, this.notifiacion);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(20);
        contenedor.setPadding(new Insets(20));

        this.setCenter(contenedor);
    }

    private void aplicarEstilo() {
        this.setPadding(new Insets(5));
    }
}
