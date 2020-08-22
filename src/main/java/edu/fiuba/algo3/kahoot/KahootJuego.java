package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.vista.escenas.controlador.ControladorVistaJuego;
import edu.fiuba.algo3.eventos.kahoot.CambioLayoutEventHandler;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaLayout;
import edu.fiuba.algo3.vista.escenas.juego.PuntajeParcialLayout;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootJuego extends KahootModo {

    private Juego juego;

    public KahootJuego(Stage stage, Pane panelPadre, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, panelPadre, preguntas, jugadores);
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

        previaPregunta.eventoSiguiente((event) -> controlador.crearEscena(jugada));

        controlador.eventoSiguiente((event) -> {
            // Obtengo las respuestas del usuario
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
                    puntajeParcial.eventoSiguiente(this.eventoSalida, "Continuar");
                }
                new CambioLayoutEventHandler(this.panelPadre, puntajeParcial).handle(null);
            }
        });
        new CambioLayoutEventHandler(this.panelPadre, previaPregunta).handle(null);

    }
}