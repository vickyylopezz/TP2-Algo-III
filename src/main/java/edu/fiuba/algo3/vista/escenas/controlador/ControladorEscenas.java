package edu.fiuba.algo3.vista.escenas.controlador;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonExclusividad;
import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.vista.componentes.contenedores.EtiquetaVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import edu.fiuba.algo3.vista.escenas.preguntas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class ControladorEscenas extends BaseEscena {
    private Stage stage;
    private VistaPregunta vista;
    private EventHandler<ActionEvent> evento;

    public ControladorEscenas(Stage stage, MediaPlayer reproductor, Jugada jugada) {
        super(reproductor);
        this.stage = stage;
    }


    public void crearUnaEscena (Jugada jugada) throws PuntoError {
        Pregunta pregunta = jugada.obtenerPregunta();
        Jugador jugador = jugada.obtenerJugador();

        // LAYOUT BORDERPANE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(40,40,40,40));
        borderPane.setStyle("-fx-background-image: url(" + "file:src/main/resources/iconos/fondo_ladrillo.png" + ");" + "-fx-background-size: cover");

        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Label indicadorJugador = new Label("Jugador: " + jugador.nombre());
        indicadorJugador.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold;");
        Label indicadorPuntaje = new Label("Puntos: " + jugador.puntajeTotal().obtenerPunto().obtenerValor());
        indicadorPuntaje.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-font-weight: bold;");
        VBox pilaDatos = new VBox(20, indicadorJugador, indicadorPuntaje);
        EtiquetaVista datosJugador = new EtiquetaVista(pilaDatos);
        datosJugador.setAlignment(Pos.TOP_LEFT);

        // BOTON CONFIRMAR (abajo en el borderPane)
        BotonCuadradoVista confirmar = new BotonCuadradoVista("Confirmar");
        confirmar.setStyle("-fx-font-weight: bold;");
        confirmar.setPrefSize(250, 50);

        // COMODINES (arriba-derecha del borderPane)
        Button multiplicadorX2 = new Button("X2");
        multiplicadorX2.setPrefSize(50,50);
        Button multiplicadorX3 = new Button("X3");
        multiplicadorX3.setPrefSize(50,50);
        BotonExclusividad exclusividad = new BotonExclusividad();
        VBox botoneraComodines = new VBox();
        botoneraComodines.getChildren().addAll(multiplicadorX2,multiplicadorX3,exclusividad);
        botoneraComodines.setSpacing(10);
        // ...
        
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

        borderPane.setRight(botoneraComodines);
        borderPane.setTop(datosJugador);
        borderPane.setCenter(vista);
        if (confirmar != null) {
            borderPane.setBottom(confirmar);
            BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);
            confirmar.setOnAction(evento);
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
        this.evento = evento;
    }
    public EventHandler<ActionEvent> getEvento() { return this.evento; }

    public void procesarJugada(Object source) {
        ArrayList<Opcion> opciones = new ArrayList<>();
        if (source instanceof BotonOpcionClasica){
            opciones.add(((BotonOpcionClasica) source).obtenerOpcion());
        } else {
            opciones.addAll(vista.obtenerSeleccion());
        }
        System.out.println(opciones);
    }

}
