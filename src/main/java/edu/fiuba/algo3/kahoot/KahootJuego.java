package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.vista.componentes.PreviaPreguntaVista;
import edu.fiuba.algo3.vista.escenas.preparacion.PreviaPreguntaEscena;
import edu.fiuba.algo3.vista.escenas.preparacion.PuntajeParcialEscena;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KahootJuego extends KahootModo {

    public KahootJuego(Stage stage, ArrayList<Pregunta> preguntas, ArrayList<Jugador> jugadores) {
        super(stage, preguntas, jugadores);
    }

    @Override
    public void iniciar(MediaPlayer reproductor) {
        Juego juego = new Juego(this.preguntas, this.jugadores);
        juego.iniciarPartidas();

        /*
        Para probar las escenas

        Jugada jugada = new Jugada(new VerdaderoFalsoClasico("Estamos en el año 2020 A.C."), new Jugador("Patricio"));
        PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
        new CambioEscenaEventHandler(this.stage, previaPregunta).handle(null);

        PuntajeParcialEscena puntajesParciales = new PuntajeParcialEscena(this.stage, reproductor, juego);
        new CambioEscenaEventHandler(this.stage, puntajesParciales).handle(null);
        */

        while(juego.existePartida()){
            Partida partida = juego.obtenerPartida();
            partida.iniciarTurnos();
            while (partida.existeTurno()){
                Jugada jugada = partida.obtenerJugada();
                PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
                new CambioEscenaEventHandler(this.stage, previaPregunta).handle(null);
            }
            PuntajeParcialEscena puntajesParciales = new PuntajeParcialEscena(this.stage, reproductor, juego);
            new CambioEscenaEventHandler(this.stage, puntajesParciales).handle(null);
        }
        //cambiar a KahootResultados
    }
}
