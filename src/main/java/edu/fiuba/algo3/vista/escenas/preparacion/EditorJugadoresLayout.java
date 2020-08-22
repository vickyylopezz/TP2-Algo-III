package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.eventos.preparacion.editorJugadores.ActualizadorVistaRegistroJugadores;
import edu.fiuba.algo3.eventos.preparacion.editorJugadores.JugadorObservable;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.escenas.BaseLayout;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class EditorJugadoresLayout extends BaseLayout {

    public EditorJugadoresLayout(ArrayList<Jugador> jugadores) {
        ArrayList<JugadorObservable> jugadoresObservables = this.crearJugadoresObservables(jugadores);
        this.setCenter(this.crearCuerpo(jugadoresObservables));
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
        contenedor.setSpacing(40);

        for (JugadorObservable jugador: jugadores) {
            int numeroJugador = jugadores.indexOf(jugador) + 1;

            JugadorVista jugadorVista = new JugadorVista(jugador, numeroJugador);
            jugadorVista.setMaxHeight(300);

            contenedor.getChildren().add(new StackPane(jugadorVista));
        }

        return contenedor;
    }

    public void habilitarEscenaSigueinte() { this.botonSigueinte.activar(); }

    public void deshabilitarEscenaSigueinte() { this.botonSigueinte.desactivar(); }

    @Override
    protected void botonSiguienteDefinido() { this.botonSigueinte.desactivar(); }
}
