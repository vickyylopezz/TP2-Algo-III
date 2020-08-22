package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.ContenedorSonido;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorVistaJuego;
import edu.fiuba.algo3.eventos.kahoot.CambioLayoutEventHandler;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaLayout;
import edu.fiuba.algo3.vista.escenas.juego.PuntajeParcialLayout;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class KahootJuego extends KahootModo {
    private MediaPlayer reproductor;
    private Juego juego;

    public KahootJuego(Stage stage, Pane panelPadre, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores, MediaPlayer reproductor) {
        super(stage, panelPadre, preguntas, jugadores);
        this.reproductor = reproductor;
    }

    @Override
    public void iniciar() {
        this.juego = new Juego(this.preguntas, this.jugadores);
        this.juego.iniciarPartidas();
        this.siguienteEscena();
    }

    private void siguienteEscena() {
        if (this.juego.existePartida()){
            this.proximaPartida();
        }
    }

    private void proximaPartida() {
        reproductor.stop();
        Partida partida = this.juego.obtenerPartida();
        partida.iniciarTurnos();
        this.juego.siguientePartida();
        if (partida.existeTurno()){
            this.proximaJugada(partida);
        } else {
            this.siguienteEscena();
        }
    }

    private void proximaJugada(Partida partida) {
        Jugada jugada = partida.obtenerJugada();
        partida.siguienteTurno();

        PreviaPreguntaLayout previaPregunta = new PreviaPreguntaLayout(jugada);
        ControladorVistaJuego controlador = new ControladorVistaJuego(this.panelPadre);

        previaPregunta.eventoSiguiente((event) -> {
            if (partida.existeTurno()) {
                cambiarCancion(Resources.MusicaPreguntaJ1Ruta(), false);
            } else {
                cambiarCancion(Resources.MusicaPreguntaJ2Ruta(), false);
            }
            controlador.crearEscena(jugada);
        });

        controlador.eventoSiguiente((event) -> {
            cambiarCancion(Resources.MusicaRespuestaRuta(), false);
            controlador.procesarJugada(event.getSource(), jugada);

            if (partida.existeTurno()){
                this.proximaJugada(partida);
            } else {
                PuntajeParcialLayout puntajeParcial;
                partida.finalizarTurnos();
                puntajeParcial = new PuntajeParcialLayout(this.stage, this.jugadores);
                if (this.juego.existePartida()){
                    puntajeParcial.eventoSiguiente((e) -> this.proximaPartida(), "Continuar");
                } else {
                    cambiarCancion(Resources.MusicaKahootRuta(), true);
                    puntajeParcial.eventoSiguiente(this.eventoSalida, "Continuar");
                }
                new CambioLayoutEventHandler(this.panelPadre, puntajeParcial).handle(null);
            }
        });
        new CambioLayoutEventHandler(this.panelPadre, previaPregunta).handle(null);

    }

    public void cambiarCancion(String resource, boolean loop){
        ((StackPane)stage.getScene().getRoot()).getChildren().remove(1);

        boolean mute = reproductor.isMute();

        reproductor.stop();
        reproductor = new MediaPlayer(new Media(new File(resource).toURI().toString()));
        if (loop) { reproductor.setOnEndOfMedia(() -> reproductor.seek(Duration.ZERO)); }
        if (mute) { reproductor.setMute(true); }
        reproductor.play();

        ContenedorSonido nuevoContenedor = new ContenedorSonido(reproductor);
        nuevoContenedor.setPickOnBounds(false);

        ((StackPane)stage.getScene().getRoot()).getChildren().add(1, nuevoContenedor);
    }

    public MediaPlayer getReproductor() {
        return reproductor;
    }
}