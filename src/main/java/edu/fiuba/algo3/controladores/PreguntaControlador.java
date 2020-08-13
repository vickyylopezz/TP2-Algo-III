package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.juego.Partida;
import edu.fiuba.algo3.vista.escenas.EstructuraPrincipalVista;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaVista;
import edu.fiuba.algo3.vista.escenas.juego.PuntosObtenidosVista;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;
/*
public class PreguntaControlador {
    private Partida partida;
    private Stage stage;

    public PreguntaControlador(Stage stage, Partida partida) {
        this.stage = stage;
        this.partida = partida;
    }

    public void siguienteEscena() {
        if (!this.partida.existeTurno()){
            stage.setScene(new PuntosObtenidosVista().obtenerEscena());
        }
        this.stage.setScene(new PreviaPreguntaVista(partida.obtenerJugada()).obtenerEscena());
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                return new PreguntaVista(this);       partida.siguienteTurno() controlador.siguienteEscena
                stage.setScene(new PuntosObtenidosVista().obtenerEscena());
            }
        };
        timer.schedule(tarea, 6000);
    }


}
*/