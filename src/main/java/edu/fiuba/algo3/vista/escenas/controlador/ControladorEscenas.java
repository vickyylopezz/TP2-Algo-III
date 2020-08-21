package edu.fiuba.algo3.vista.escenas.controlador;

import edu.fiuba.algo3.eventos.juego.SeleccionarComodinEventHandler;
import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.comodines.Exclusividad;
import edu.fiuba.algo3.modelo.comodines.Multiplicador;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.VerdaderoFalso;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonCircularVista;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
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

    public void crearUnaEscena (Jugada jugada) {
        Pregunta pregunta = jugada.obtenerPregunta();
        Jugador jugador = jugada.obtenerJugador();

        // LAYOUT ANCHORPANE
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url(" + Resources.FondoPrincipalRuta() + ");" + "-fx-background-size: cover");

        // ---------------------- TOP-BAR: pregunta
        VBox topBar = new VBox();
        topBar.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 150);

        Label indicadorTipoPregunta = new Label(pregunta.obtenerTipo());
        indicadorTipoPregunta.setPadding(new Insets(10,50,10,50));
        indicadorTipoPregunta.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-font-weight: bold;");
        indicadorTipoPregunta.setWrapText(true);
        indicadorTipoPregunta.setTextAlignment(TextAlignment.CENTER);

        Label indicadorPregunta = new Label(pregunta.obtenerTitulo());
        indicadorPregunta.setPadding(new Insets(10,50,10,50));
        indicadorPregunta.setStyle("-fx-text-fill: #000000; -fx-font-size: 20; -fx-font-weight: bold;");
        indicadorPregunta.setWrapText(true);
        indicadorPregunta.setTextAlignment(TextAlignment.CENTER);

        topBar.getChildren().addAll(indicadorTipoPregunta, indicadorPregunta);
        topBar.setAlignment(Pos.CENTER);
        setBackgroundColor(topBar, Color.WHITE);
        // ---------------------------------------------------

        // ---------------------- SIDE-BAR: indicadores, tiempo, comodines
        BorderPane sideBar = new BorderPane();
        sideBar.setPrefSize(200, Screen.getPrimary().getVisualBounds().getHeight());
        sideBar.setPadding(new Insets(10,25,0,25));
        setBackgroundColor(sideBar, Color.WHITE);

        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Label indicadorJugador = new Label(jugador.nombre());
        indicadorJugador.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");
        indicadorJugador.setAlignment(Pos.CENTER);
        indicadorJugador.setPrefSize(150,50);

        Label indicadorPuntaje = new Label(jugador.puntajeTotal().obtenerValor().toString());
        indicadorPuntaje.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: black");
        indicadorPuntaje.setAlignment(Pos.CENTER);
        indicadorPuntaje.setPrefSize(150,50);

        VBox datosJugador = new VBox(10, indicadorJugador, indicadorPuntaje);

        // TIMER
        StackPane timer = new StackPane();
        Label timerLabel = new Label();
        IntegerProperty timeSeconds = new SimpleIntegerProperty(30);
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setPrefWidth(150);
        timerLabel.setStyle("-fx-font-size: 4em;");

        Circle circulo = new Circle(75);
        circulo.setFill(Color.WHITE);

        timer.getChildren().addAll(circulo, timerLabel);

        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(30 + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(eventoConfirmar);

        // COMODINES
        VBox comodines = new VBox(20);
        for (Comodin comodin: jugada.comodines()) {
            BotonCircularVista boton = new BotonCircularVista(comodin.toString(), Color.INDIANRED);
            comodines.getChildren().add(boton);

            boton.setOnAction(new SeleccionarComodinEventHandler(
                    jugada,
                    comodin,
                    comodines.getChildren(),
                    boton
            ));
        }

        comodines.setPadding(new Insets(0,50,25,50));

        sideBar.setTop(datosJugador);
        sideBar.setCenter(timer);
        sideBar.setBottom(comodines);
        BorderPane.setAlignment(comodines, Pos.BOTTOM_CENTER);
        // ---------------------------------------------------


        // ---------------------- VISTA: opciones + confirmar (opcional)
        // PREGUNTA + OPCIONES (centro del borderPane)
        if (pregunta.getClass() == VerdaderoFalso.class) {
            vista = new VistaVerdaderoFalso(pregunta, pregunta.obtenerOpciones(), this);

        } else if (pregunta.getClass() == OrderedChoice.class) {
            vista = new VistaOrderedChoice(pregunta, pregunta.obtenerOpciones(), this);

        } else if (pregunta.getClass() == GroupChoice.class) {
            vista = new VistaGroupChoice(pregunta, pregunta.obtenerOpciones(), this);

        } else {
            vista = new VistaMultipleChoice(pregunta, pregunta.obtenerOpciones(), this);
        }
        // ---------------------------------------------------

        // ASIGNACIONES DEL ANCHORPANE
        AnchorPane.setLeftAnchor(sideBar, 0d);
        AnchorPane.setBottomAnchor(sideBar, 0d);
        AnchorPane.setTopAnchor(sideBar, 0d);

        AnchorPane.setRightAnchor(topBar, 0d);
        AnchorPane.setLeftAnchor(topBar, 200d);

        AnchorPane.setTopAnchor(vista, 150d);
        AnchorPane.setRightAnchor(vista, 0d);
        AnchorPane.setBottomAnchor(vista, 0d);
        AnchorPane.setLeftAnchor(vista, 200d);

        anchorPane.getChildren().addAll(sideBar, topBar, vista);

        stage.setScene(new Scene(anchorPane, 1280,800));
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
        for (Opcion opcion: opciones) {
            jugada.seleccionarOpcion(opcion);
        }
    }

    public static void setBackgroundColor(Region region, Color color) {
        // change to 50% opacity
        color = color.deriveColor(0, 1, 1, 0.5);
        region.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
