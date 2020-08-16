package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.eventos.preparacion.editorJugadores.ActualizadorVistaRegistroJugadores;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.JugadorObservable;
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

        ArrayList<JugadorObservable> jugadoresObservables = this.crearJugadoresObservables(jugadores);
        this.raiz.setCenter(this.crearCuerpo(jugadoresObservables));
    }

    private ArrayList<JugadorObservable> crearJugadoresObservables(ArrayList<Jugador> jugadores) {
        ArrayList<JugadorObservable> jugadoresObservables = new ArrayList<>();
        ActualizadorVistaRegistroJugadores observador =
                new ActualizadorVistaRegistroJugadores(jugadores, this);

        for (Jugador jugador: jugadores) {
            JugadorObservable jugadorObservable = new JugadorObservable(jugador);
            jugadorObservable.agregarObservador(observador);
            jugadoresObservables.add(jugadorObservable);
        }

        return jugadoresObservables;
    }

    private Node crearCuerpo(ArrayList<JugadorObservable> jugadores) {
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);

        for (JugadorObservable jugador: jugadores) {
            int numeroJugador = jugadores.indexOf(jugador) + 1;
            contenedor.getChildren().add(new JugadorVista(jugador, numeroJugador));
        }

        return contenedor;
    }

    public void habilitarEscenaSigueinte() {
        this.botonSigueinte.setDisable(false);
    }

    public void deshabilitarEscenaSigueinte() {
        this.botonSigueinte.setDisable(true);
    }

    @Override
    protected void botonSigueinteDefinido() { this.botonSigueinte.setDisable(true); }
}
