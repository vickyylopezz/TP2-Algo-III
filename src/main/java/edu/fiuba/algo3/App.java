package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;
import edu.fiuba.algo3.vista.escenas.CargarPreguntasVista;
import edu.fiuba.algo3.vista.escenas.Escena;
import edu.fiuba.algo3.vista.escenas.PreviaPreguntaVista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Escena cargadorPreguntas = new CargarPreguntasVista();
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Cuál es el apellido de nuestro corrector?");
        Jugador jugador = new Jugador("Carlos");
        Jugada jugada = new Jugada(pregunta, jugador);
        Escena previaPregunta = new PreviaPreguntaVista(jugada);

        /*
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        */

        stage.setScene(cargadorPreguntas.obtenerEscena());
        /*
        stage.setScene(previaPregunta.obtenerEscena());
        stage.show();
        timer.schedule(tarea, 6000);
         */
    }

    public static void main(String[] args) {
        launch();
    }

}