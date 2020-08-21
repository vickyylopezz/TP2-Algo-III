package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.vista.escenas.controlador.ControladorEscenas;
import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaLayout;
import edu.fiuba.algo3.vista.escenas.juego.PuntajeParcialLayout;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootJuego extends KahootModo {
    private Juego juego;

    public KahootJuego(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);
    }

    @Override
    public void iniciar(MediaPlayer reproductor) {
        this.juego = new Juego(this.preguntas, this.jugadores);
        this.juego.iniciarPartidas();
        this.siguienteEscena(reproductor);
    }

    private void siguienteEscena(MediaPlayer reproductor) {
        if (this.juego.existePartida()){
            this.proximaPartida(reproductor);
        } else {
            this.finalizarJuego(reproductor);
        }
    }

    private void proximaPartida(MediaPlayer reproductor) {
        Partida partida = this.juego.obtenerPartida();
        partida.iniciarTurnos();
        this.juego.siguientePartida();
        if (partida.existeTurno()){
            this.proximaJugada(reproductor, partida);
        } else {
            this.siguienteEscena(reproductor);
        }
    }

    private void proximaJugada(MediaPlayer reproductor, Partida partida) {
        Jugada jugada = partida.obtenerJugada();
        partida.siguienteTurno();

        PreviaPreguntaLayout previaPregunta = new PreviaPreguntaLayout(reproductor, jugada);
        ControladorEscenas controlador = new ControladorEscenas(stage, reproductor);

        previaPregunta.eventoSiguiente((event) -> controlador.crearEscena(jugada));

        controlador.eventoSiguiente((event) -> {
            // Obtengo las respuestas del usuario
            controlador.procesarJugada(event.getSource(), jugada);

            if (partida.existeTurno()){
                this.proximaJugada(reproductor, partida);
            } else {
                PuntajeParcialLayout puntajeParcial;
                partida.finalizarTurnos();
                puntajeParcial = new PuntajeParcialLayout(this.stage, reproductor, this.jugadores);
                if (this.juego.existePartida()){
                    puntajeParcial.eventoSiguiente((e) -> this.proximaPartida(reproductor), "Continuar");
                } else {
                    puntajeParcial.eventoSiguiente(this.eventoSalida, "Continuar");
                }
                new CambioEscenaEventHandler(this.stage, puntajeParcial).handle(null);
            }
        });
        new CambioEscenaEventHandler(this.stage, previaPregunta).handle(null);

    }

    private void finalizarJuego(MediaPlayer reproductor){
    }
}