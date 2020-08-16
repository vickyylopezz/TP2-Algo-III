package edu.fiuba.algo3.vista.escenas.resultados;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.GanadorVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

import static javafx.geometry.Pos.CENTER;

public class UltimaEscena extends BaseEscena {
    private StackPane cuerpo;
    public UltimaEscena(MediaPlayer reproductor, Jugador jugador1, Jugador jugador2) {
        super(reproductor);
        Node cuerpo = this.crearCuerpo(jugador1,jugador2);
        this.raiz.setCenter(cuerpo);

    }

    private Node crearCuerpo(Jugador jugador1, Jugador jugador2) {
        //TituloGanador
        Label tituloGanador = new Label("G A N A D O R");
        tituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
        tituloGanador.setAlignment(Pos.TOP_CENTER);

        //JugadorGanador
        GanadorVista ganador = new GanadorVista();

        //XBox
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(tituloGanador,ganador.obtenerNodo());
        vbox.setAlignment(CENTER);

        return vbox;
    }
    
}
