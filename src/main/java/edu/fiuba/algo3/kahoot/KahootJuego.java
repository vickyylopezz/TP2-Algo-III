package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
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

    public KahootJuego(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores, Juego juego) {
        super(stage, preguntas, jugadores,juego);
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

        PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
        //el proximo evento hice para probar pero mas o menos esto tendria que hacer el confirmar de pregunta
        //ademas de guardar las respuestas de los jugadores y aplicar comodin
        //se puede hacer una clase que tenga el condicional y reciba la partida

        previaPregunta.eventoSiguiente((event) -> {
            if (partida.existeTurno()){
                this.proximaJugada(reproductor, partida);
            } else {
                PuntajeParcialEscena puntajeParcial = null;
                try {
                    puntajeParcial = new PuntajeParcialEscena(this.stage, reproductor, this.jugadores);
                } catch (PuntoError puntoError) {
                    puntoError.printStackTrace();
                }
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

    // NO SE SI HACE FALTA ESTE METODO
    // SI NO SE USA HABRIA QUE SACAR EL ELSE EN proximaPartida();
    private void finalizarJuego(MediaPlayer reproductor){
    }
}