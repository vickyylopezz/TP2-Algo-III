package edu.fiuba.algo3.vista.escenas.resultados;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.EmpateVista;
import edu.fiuba.algo3.vista.componentes.GanadorVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;


import static javafx.geometry.Pos.CENTER;

public class UltimaEscena extends BaseEscena {
    private StackPane cuerpo;
    public UltimaEscena(MediaPlayer reproductor, Jugador jugador1, Jugador jugador2, Juego juego) throws PuntoError {
        super(reproductor);
        Node cuerpo = this.crearCuerpo(jugador1,jugador2,juego);
        this.raiz.setCenter(cuerpo);

    }

    private Node crearCuerpo(Jugador jugador1, Jugador jugador2,Juego juego) throws PuntoError {
        try{
            //TituloGanador
            Label tituloGanador = new Label("G A N A D O R");
            tituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
            tituloGanador.setAlignment(Pos.TOP_CENTER);

            //JugadorGanador
            GanadorVista ganador = new GanadorVista(juego.ganador(jugador1,jugador2).nombre());

            //XBox
            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(tituloGanador,ganador.obtenerNodo());
            vbox.setAlignment(CENTER);

            return vbox;
        }catch(PuntoError empateExcepcion){
            Label tituloEmpate = new Label("E M P A T E");
            tituloEmpate.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
            tituloEmpate.setAlignment(Pos.TOP_CENTER);

            EmpateVista empate = new EmpateVista(jugador1,jugador2);

            VBox vbox = new VBox(20);

            vbox.getChildren().addAll(tituloEmpate,empate.obtenerNodo());
            vbox.setAlignment(CENTER);

            return vbox;
        }

    }
    
}
