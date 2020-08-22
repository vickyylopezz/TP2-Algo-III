package edu.fiuba.algo3.vista.escenas.resultados;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.EmpateVista;
import edu.fiuba.algo3.vista.componentes.GanadorVista;
import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;


import java.util.ArrayList;

import static javafx.geometry.Pos.CENTER;

public class UltimaLayout extends BaseLayout {

    private StackPane cuerpo;

    public UltimaLayout(ArrayList<Jugador> jugadores, Juego juego) {
        Node cuerpo = this.crearCuerpo(jugadores,juego);
        this.setCenter(cuerpo);
    }

    private Node crearCuerpo(ArrayList<Jugador> jugadores,Juego juego) {
        if(juego.ganador(jugadores).size() < 2){
            //TituloGanador
            Label tituloGanador = new Label("G A N A D O R");
            tituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
            tituloGanador.setAlignment(Pos.TOP_CENTER);

            //JugadorGanador
            GanadorVista ganador = new GanadorVista(jugadores.get(0).nombre());

            //XBox
            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(tituloGanador,ganador.obtenerNodo());
            vbox.setAlignment(CENTER);

            return vbox;
        }else{
            Label tituloEmpate = new Label("E M P A T E");
            tituloEmpate.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
            tituloEmpate.setAlignment(Pos.TOP_CENTER);

            EmpateVista empate = new EmpateVista(jugadores.get(0),jugadores.get(1));

            VBox vbox = new VBox(20);

            vbox.getChildren().addAll(tituloEmpate,empate.obtenerNodo());
            vbox.setAlignment(CENTER);

            return vbox;
        }
     }
    
}
