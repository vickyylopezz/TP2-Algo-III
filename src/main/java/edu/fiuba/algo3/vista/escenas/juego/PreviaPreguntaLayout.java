package edu.fiuba.algo3.vista.escenas.juego;

import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.vista.componentes.contenedores.CajaVista;
import edu.fiuba.algo3.vista.componentes.contenedores.EtiquetaVista;
import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

public class PreviaPreguntaLayout extends BaseLayout {

    public PreviaPreguntaLayout(Jugada jugada) {
        Node cuerpo = this.crearCuerpo(jugada);
        this.setCenter(cuerpo);
    }

    private Node crearCuerpo(Jugada jugada) {
        VBox vbox = new VBox(100);
        vbox.setPadding(new Insets(70));
        Label jLabelTipoPregunta = new Label(jugada.obtenerPregunta().obtenerTipo());
        jLabelTipoPregunta.setAlignment(Pos.CENTER);
        jLabelTipoPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold");

        TextArea tituloPregunta = new TextArea(jugada.tituloPregunta());
        tituloPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold;");
        tituloPregunta.setWrapText(true);
        tituloPregunta.setEditable(false);

        CajaVista cajaVistaPregunta = new CajaVista();
        cajaVistaPregunta.setCenter(tituloPregunta);

        CajaVista cajaVistaTipo = new CajaVista();
        cajaVistaTipo.setCenter(jLabelTipoPregunta);

        vbox.getChildren().addAll(cajaVistaTipo, cajaVistaPregunta);
        vbox.setAlignment(Pos.CENTER);

        Label jLabelTurno = new Label("Turno de " + jugada.obtenerJugador().nombre());
        jLabelTurno.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18;");
        EtiquetaVista turno = new EtiquetaVista(jLabelTurno);

        this.cabecera.definirPanelIzquierdo(turno);

        return vbox;
    }
}
