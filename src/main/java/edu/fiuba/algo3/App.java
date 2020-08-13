package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.IniciarJuegoControlador;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaVista;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException, PreguntaError {



        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        //Escena puntosobtenidos = new PuntosObtenidosVista();
        //Escena ganador = new GanadorVista();

        //Jugada jugada = new Jugada(new VerdaderoFalsoConPenalidad("Estamos en el año 2021"), new Jugador("Carlos"));
        //Escena previaPregunta = new PreviaPreguntaVista(jugada);

        //stage.setScene(crearJuego.obtenerEscena());
        //stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        //stage.setScene(registrarJugadores.obtenerEscena());
        //stage.setScene(puntosobtenidos.obtenerEscena());
        //stage.setScene(previaPregunta.obtenerEscena());
        //stage.setScene(ganador.obtenerEscena());
        //stage.show();

        //CargarPreguntaControlador preguntasControlador = new CargarPreguntaControlador(stage);
        //preguntasControlador.mostrarVista();

        IniciarJuegoControlador iniciarJuegoControlador = new IniciarJuegoControlador(stage);
        iniciarJuegoControlador.mostrarVista();
        comenzarMusica();

    }
    public static void comenzarMusica(){
         Media sonido = new Media(new File("./src/main/resources/Kahoot.wav").toURI().toString());
         MediaPlayer reproductor = new MediaPlayer(sonido);
         reproductor.play();
     }
    public static void main(String[] args) {
        launch();
    }

}