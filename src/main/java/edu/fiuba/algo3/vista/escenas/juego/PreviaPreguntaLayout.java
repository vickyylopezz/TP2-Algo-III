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
import javafx.scene.text.TextAlignment;

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
        jLabelTipoPregunta.setPrefSize(400,100);
        jLabelTipoPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold");

        Label tituloPregunta = new Label(jugada.tituloPregunta());
        tituloPregunta.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 24; -fx-font-weight: bold;");
        tituloPregunta.setPrefSize(1000,500);
        tituloPregunta.setAlignment(Pos.CENTER);
        tituloPregunta.setTextAlignment(TextAlignment.CENTER);
        tituloPregunta.setPadding(new Insets(50));
        tituloPregunta.setWrapText(true);

        CajaVista cajaVistaTipo = new CajaVista();
        cajaVistaTipo.setCenter(jLabelTipoPregunta);
        cajaVistaTipo.setMaxSize(400, 100);

        CajaVista cajaVistaPregunta = new CajaVista();
        cajaVistaPregunta.setCenter(tituloPregunta);
        cajaVistaPregunta.setMaxSize(1000, 500);



        vbox.getChildren().addAll(cajaVistaTipo, cajaVistaPregunta);
        vbox.setAlignment(Pos.CENTER);

        Label jLabelTurno = new Label("Turno de " + jugada.obtenerJugador().nombre());
        jLabelTurno.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18;");
        EtiquetaVista turno = new EtiquetaVista(jLabelTurno);

        this.cabecera.definirPanelIzquierdo(turno);

        return vbox;
    }
}
