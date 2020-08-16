package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaEscena;
import edu.fiuba.algo3.vista.escenas.juego.PuntajeParcialEscena;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class KahootJuego extends KahootModo {

    private final Juego juego;

    public KahootJuego(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);
        this.juego = new Juego(this.preguntas, this.jugadores);
    }

    @Override
    public void iniciar(MediaPlayer reproductor) {
        this.juego.iniciarPartidas();
        this.siguienteEscena(reproductor);

        /*
        Para probar las escenas

        Jugada jugada = new Jugada(new VerdaderoFalsoClasico("Estamos en el año 2020 A.C."), new Jugador("Patricio"));
        PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
        new CambioEscenaEventHandler(this.stage, previaPregunta).handle(null);

        PuntajeParcialEscena puntajesParciales = new PuntajeParcialEscena(this.stage, reproductor, juego);
        new CambioEscenaEventHandler(this.stage, puntajesParciales).handle(null);


        while(juego.existePartida()){
            Partida partida = juego.obtenerPartida();
            partida.iniciarTurnos();
            while (partida.existeTurno()){
                Jugada jugada = partida.obtenerJugada();
                PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
                new CambioEscenaEventHandler(this.stage, previaPregunta).handle(null);
                partida.siguienteTurno();
            }
            PuntajeParcialEscena puntajesParciales = new PuntajeParcialEscena(this.stage, reproductor, juego);
            new CambioEscenaEventHandler(this.stage, puntajesParciales).handle(null);
        }
        //cambiar a KahootResultados
         */
    }

    private void siguienteEscena(MediaPlayer reproductor) {
        if (this.juego.existePartida()){
            this.proximaPartida(reproductor);
        } else {
            this.finalizarJuego(juego);
        }
    }

    private void proximaPartida(MediaPlayer reproductor) {
        Partida partida = this.juego.obtenerPartida();
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

        PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
        new CambioEscenaEventHandler(this.stage, previaPregunta);

        if (partida.existeTurno()){
            this.proximaJugada(reproductor, partida);
        } else {
            this.proximaPartida(reproductor);
        }
    }

    private void finalizarJuego(Juego juego) {
        //cambiar a KahootResultados
    }
}
