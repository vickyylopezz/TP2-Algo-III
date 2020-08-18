package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class ControladorEscenas {
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Pregunta preguntaActual;
    private ListIterator<Pregunta> iterador;
    private ArrayList<Opcion> opciones;
    private Stage stage;

    public ControladorEscenas(Stage stage, Scene escenaResultados, Scene ultimaEscena) {
        this.stage = stage;
        //
        //
    }

    public void cargarDatos (ArrayList<Jugador> jugadores, ArrayList<Pregunta> preguntas) {
        this.jugadores = jugadores;
        this.jugadorActual = jugadores.get(0);

        Collections.shuffle(preguntas);
        this.iterador = preguntas.listIterator();
        this.preguntaActual = iterador.next();
        this.opciones = obtenerOpciones(preguntaActual);
    }

    public void crearUnaEscena () {
        //this.preguntaActual = iterador.next();
        //cosas comunes a un escenario de preguntas
        //jugador: nombre y puntaje
        //comodines: botones
        //pregunta: indicador

        // LAYOUT BORDERPANE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20,20,20,20));

        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Text indicadorJugador = new Text("Jugador: " + jugadorActual.nombre());
        indicadorJugador.setFont(new Font(15));
        Text indicadorPuntaje = new Text("Puntos: " + jugadorActual.puntajeTotal().obtenerValor());
        indicadorPuntaje.setFont(new Font(15));
        VBox datosJugador = new VBox(20, indicadorJugador, indicadorPuntaje);
        datosJugador.setAlignment(Pos.TOP_LEFT);

        // BOTON CONFIRMAR (abajo en el borderPane)
        Button confirmar = new Button("Confirmar");

        // PREGUNTA + OPCIONES (centro del borderPane)
        VistaPregunta vista;
        if (preguntaActual.getClass() == VerdaderoFalsoClasico.class || preguntaActual.getClass() == VerdaderoFalsoConPenalidad.class) {
            vista = new VistaVerdaderoFalso(this.preguntaActual, this.opciones, this);
            confirmar = null;

        } else if (preguntaActual.getClass() == OrderedChoice.class) {
            vista = new VistaOrderedChoice(this.preguntaActual, this.opciones, this);

        } else if (preguntaActual.getClass() == GroupChoice.class) {
            vista = new VistaGroupChoice(this.preguntaActual, this.opciones, this);

        } else {
            vista = new VistaMultipleChoice(this.preguntaActual, this.opciones, this);
        }

        borderPane.setTop(datosJugador);
        borderPane.setCenter(vista);
        if (confirmar != null) {
            borderPane.setBottom(confirmar);
            BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);
            confirmar.setOnAction(new BotonConfirmarEventHandler(this, vista));
        }

        stage.setScene(new Scene(borderPane, 1080,720));
    }

    public ArrayList<Opcion> obtenerOpciones(Pregunta pregunta) {
        ArrayList<Opcion> opciones = new ArrayList<>();
        if (pregunta.getClass() == GroupChoice.class) {
            ArrayList<Grupo> grupos= ((GroupChoice) pregunta).obtenerGrupos();
            opciones.addAll(grupos.get(0).obtenerOpciones());
        } else {
            opciones = pregunta.obtenerOpciones();
        }
        Collections.shuffle(opciones);
        return opciones;
    }

    public Stage getStage() { return this.stage; }
    public ListIterator<Pregunta> getIterador() { return this.iterador; }

    public void actualizarAtributos(){
        if (jugadorActual.equals(jugadores.get(1))) {
            jugadorActual = jugadores.get(0);
            preguntaActual = iterador.next();
            opciones = obtenerOpciones(preguntaActual);
        } else {
            jugadorActual = jugadores.get(1);
        }
    }
}
