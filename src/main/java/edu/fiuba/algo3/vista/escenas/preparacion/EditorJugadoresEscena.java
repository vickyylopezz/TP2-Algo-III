package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class EditorJugadoresEscena extends BaseEscena {

    public EditorJugadoresEscena(ArrayList<Jugador> jugadores, MediaPlayer reproductor) {
        super(reproductor);

        this.raiz.setCenter(this.crearCuerpo(jugadores));
    }

    private Node crearCuerpo(ArrayList<Jugador> jugadores) {
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);

        for (Jugador jugador: jugadores) {
            int numeroJugador = jugadores.indexOf(jugador) + 1;
            contenedor.getChildren().add(new JugadorVista(numeroJugador));
        }

        return contenedor;
    }
}
