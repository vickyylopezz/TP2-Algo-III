package edu.fiuba.algo3.vista.escenas.controlador;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import edu.fiuba.algo3.vista.escenas.preguntas.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class ControladorEscenas extends BaseEscena {
    private Stage stage;
    private VistaPregunta vista;
    private EventHandler<ActionEvent> eventoConfirmar;

    private Timeline timeline;

    public ControladorEscenas(Stage stage, MediaPlayer reproductor) {
        super(reproductor);
        this.stage = stage;
    }

    public void crearUnaEscena (Jugada jugada) throws PuntoError {
        Pregunta pregunta = jugada.obtenerPregunta();
        Jugador jugador = jugada.obtenerJugador();

        // LAYOUT BORDERPANE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(40,40,40,40));
        borderPane.setStyle("-fx-background-image: url(" + Resources.FondoRuta() + ");" + "-fx-background-size: cover");

        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Label indicadorJugador = new Label("Jugador: " + jugador.nombre());
        indicadorJugador.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold;");
        Label indicadorPuntaje = new Label("Puntos: " + jugador.puntajeTotal().obtenerPunto().obtenerValor());
        indicadorPuntaje.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold;");
        VBox datosJugador = new VBox(20, indicadorJugador, indicadorPuntaje);
        datosJugador.setAlignment(Pos.TOP_LEFT);

        // BOTON CONFIRMAR (abajo en el borderPane)
        Button confirmar = new Button("Confirmar");

        // COMODINES (arriba-derecha del borderPane)
        // ...

        // TIMER
        Label timerLabel = new Label();
        IntegerProperty timeSeconds = new SimpleIntegerProperty(15);
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setPrefWidth(200);
        timerLabel.setStyle("-fx-font-size: 4em;");

        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(15 + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(eventoConfirmar);
        
        // PREGUNTA + OPCIONES (centro del borderPane)
        if (pregunta.getClass() == VerdaderoFalsoClasico.class || pregunta.getClass() == VerdaderoFalsoConPenalidad.class) {
            vista = new VistaVerdaderoFalso(pregunta, pregunta.obtenerOpciones(), this);
            confirmar= null;

        } else if (pregunta.getClass() == OrderedChoice.class) {
            vista = new VistaOrderedChoice(pregunta, pregunta.obtenerOpciones(), this);

        } else if (pregunta.getClass() == GroupChoice.class) {
            vista = new VistaGroupChoice(pregunta, pregunta.obtenerOpciones(), this);

        } else {
            vista = new VistaMultipleChoice(pregunta, pregunta.obtenerOpciones(), this);
        }

        borderPane.setTop(datosJugador);
        datosJugador.getChildren().add(timerLabel);
        borderPane.setCenter(vista);
        if (confirmar != null) {
            borderPane.setBottom(confirmar);
            BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);
            confirmar.setOnAction(eventoConfirmar);
        }

        stage.setScene(new Scene(borderPane, 1280,720));
    }

    public ArrayList<Opcion> obtenerOpciones(Pregunta pregunta) {
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        if (pregunta.getClass() != GroupChoice.class) {
            Collections.shuffle(opciones);
        }
        return opciones;
    }

    public Stage getStage() { return this.stage; }
    public VistaPregunta getVista() { return this.vista; }

    public void eventoSiguiente(EventHandler<ActionEvent> evento) {
        this.eventoConfirmar = evento;
    }
    public EventHandler<ActionEvent> getEvento() { return this.eventoConfirmar; }

    public void procesarJugada(Object source, Jugada jugada) {
        timeline.stop();
        ArrayList<Opcion> opciones = new ArrayList<>();
        if (source instanceof BotonOpcionClasica){
            opciones.add(((BotonOpcionClasica) source).obtenerOpcion());
        } else {
            opciones.addAll(vista.obtenerSeleccion());
        }
        Respuesta respuesta = new Respuesta(jugada.obtenerPregunta(), jugada.obtenerJugador());
        for (Opcion opcion: opciones) {
            respuesta.agregarOpcion(opcion);
        }
        jugada.obtenerJugador().agregarRespuesta(respuesta);
    }

}
