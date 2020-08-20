package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorEscenas;
import edu.fiuba.algo3.eventos.kahoot.CambioEscenaEventHandler;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaEscena;
import edu.fiuba.algo3.vista.escenas.juego.PuntajeParcialEscena;
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

        PreviaPreguntaEscena previaPregunta = new PreviaPreguntaEscena(reproductor, jugada);
        ControladorEscenas controlador = new ControladorEscenas(stage, reproductor);

        previaPregunta.eventoSiguiente((event) -> {
            try {
                controlador.crearUnaEscena(jugada);
            } catch (PuntoError puntoError) {
                puntoError.printStackTrace();
            }
        });

        controlador.eventoSiguiente((event) -> {
            // Obtengo las respuestas del usuario
            controlador.procesarJugada(event.getSource(), jugada);

            /*Object source = event.getSource();
            ArrayList<Opcion> opciones = new ArrayList<>();
            if (source instanceof BotonOpcionClasica){
                opciones.add(((BotonOpcionClasica) source).obtenerOpcion());
            } else {
                opciones.addAll(controlador.getVista().obtenerSeleccion());
            }
            Respuesta respuesta = new Respuesta(jugada.obtenerPregunta(), jugada.obtenerJugador());
            for (Opcion opcion: opciones) {
                jugada.seleccionarOpcion(opcion);
            }
            jugada.obtenerJugador().agregarRespuesta(respuesta);*/

            if (partida.existeTurno()){
                this.proximaJugada(reproductor, partida);
            } else {
                PuntajeParcialEscena puntajeParcial = null;
                try {
                    partida.finalizarTurnos();
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

    private void finalizarJuego(MediaPlayer reproductor){
    }
}