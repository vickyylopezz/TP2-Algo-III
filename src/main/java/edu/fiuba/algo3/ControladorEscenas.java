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
        this.preguntaActual = this.iterador.next();
    }

    public void crearUnaEscena () {
        //cosas comunes a un escenario de preguntas
        //jugador: nombre y puntaje
        //comodines: botones
        //pregunta: indicador

        // para verdadero falso:
        // dos botones, cada uno actua como confirmar

        // multiple choice:
        // muchos botones, mas un boton confirmar

        // ordered choice:
        // etiquetas que suben y bajan, en un unico vbox, boton confirmar
        // o sino, tocar dos botones para reemplazar sus posiciones ( ESTA FUNCIONA )

        // group choice:
        // primer click selecciona la opcion, segundo click selecciona el grupo
        // las cosas se ordenan en un grid pane como columnas ( funcionará? )


        // el layout general es un borderpane, y esta en esta altura
        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Text indicadorJugador = new Text("Jugador 1: " + jugadorActual.nombre());
        indicadorJugador.setFont(new Font(15));
        Text indicadorPuntaje = new Text("Puntos: " + jugadorActual.puntajeTotal().obtenerValor());
        indicadorPuntaje.setFont(new Font(15));
        VBox datosJugador = new VBox(20, indicadorJugador, indicadorPuntaje);
        datosJugador.setAlignment(Pos.TOP_LEFT);

        // LAYOUT BORDERPANE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20,20,20,20));

        VBox vista;
        Button confirmar = new Button("Confirmar");
        // el vbox, depende de la clase
        // incluye pregunta y botones segun la posicion caracteristica (y botno confirmar)
        if (preguntaActual.getClass() == VerdaderoFalsoClasico.class || preguntaActual.getClass() == VerdaderoFalsoConPenalidad.class) {
        //    vista = new EscenaVerdaderoFalso(this.preguntaActual, obtenerOpciones(preguntaActual));
            vista = new VistaMultipleChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
            //confirmar = null;
        } else if (preguntaActual.getClass() == OrderedChoice.class) {
        //    vista = new EscenaOrderedChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
            vista = new VistaMultipleChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
        } else if (preguntaActual.getClass() == GroupChoice.class) {
        //    vista = new EscenaGroupChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
            vista = new VistaMultipleChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
        } else {
            vista = new VistaMultipleChoice(this.preguntaActual, obtenerOpciones(preguntaActual));
        }

        borderPane.setTop(datosJugador);
        borderPane.setCenter(vista);
        if (confirmar != null) {
            // condicion del boton
            confirmar.setOnAction(e -> {
                if (iterador.hasNext()) {
                    this.preguntaActual = iterador.next();
                    crearUnaEscena();
                }
            });
            borderPane.setBottom(confirmar);
            BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);
        }

        stage.setScene(new Scene(borderPane, 1080,720));
    }

    public ControladorEscenas(ArrayList<Jugador> jugadores, ArrayList<Pregunta> preguntas) {
        this.jugadores = jugadores;

        Collections.shuffle(preguntas);
        this.iterador = preguntas.listIterator();
        this.preguntaActual = this.iterador.next();

        //this.botones = obtenerBotones(obtenerOpciones(this.preguntaActual));
    }

    public Scene crearEscena() {
        // JUGADOR ACTUAL + PUNTAJE (arriba-izquierda del borderPane)
        Text indicadorJugador = new Text("Jugador 1: " + jugadores.get(0).nombre());
        indicadorJugador.setFont(new Font(15));
        Text indicadorPuntaje = new Text("Puntos: " + jugadores.get(0).puntajeTotal().obtenerValor());
        indicadorPuntaje.setFont(new Font(15));
        VBox datosJugador = new VBox(20, indicadorJugador, indicadorPuntaje);
        datosJugador.setAlignment(Pos.TOP_LEFT);

        // PREGUNTA
        Text indicadorPregunta = new Text(this.preguntaActual.obtenerTitulo());

        // OPCIONES
        ArrayList<Opcion> opciones = obtenerOpciones(this.preguntaActual);
        // BOTONES PARA LAS OPCIONES
        ArrayList<ToggleButton> botones = obtenerBotones(opciones);

        // LAYOUT VBOX (indicadorPregunta + gridPane)
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        // BOTON PARA CONFIRMAR (centro-abajo del borderPane)
        Button confirmar = new Button("Confirmar");
        //confirmar.setAlignment(Pos.CENTER);

        // GRIDPANE EN LUGAR DE VBOX
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // LAYOUT BORDERPANE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20,20,20,20));

        borderPane.setTop(datosJugador);
        borderPane.setCenter(vbox);
        borderPane.setBottom(confirmar);
        borderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);



        confirmar.setOnAction(e -> {
            if (iterador.hasNext()) {
                // estas son las que se limpian por cada pregunta
                vbox.getChildren().clear();
                gridPane.getChildren().clear();

                this.preguntaActual = iterador.next();

                indicadorPregunta.setText(this.preguntaActual.obtenerTitulo());
                vbox.getChildren().add(indicadorPregunta);

                ArrayList<ToggleButton> nuevosBotones = obtenerBotones(obtenerOpciones(this.preguntaActual));
                for (int i = 0; i < nuevosBotones.size(); i++){
                    if ((i == (nuevosBotones.size()-1)) && (i % 2 == 0)) {
                        nuevosBotones.get(i).setPrefWidth((nuevosBotones.get(i).getPrefWidth())*2 + 20);
                        gridPane.add(nuevosBotones.get(i), 0, i / 2,2,1);
                    } else {
                        gridPane.add(nuevosBotones.get(i), i % 2, i / 2);
                    }
                }
                vbox.getChildren().add(gridPane);

            } else {
                System.exit(0);
            }
        });

        vbox.getChildren().add(indicadorPregunta);
        //vbox.getChildren().add(confirmar);

        for (int i = 0; i < botones.size(); i++){
            gridPane.add(botones.get(i), i%2, i/2);
        }
        vbox.getChildren().add(gridPane);

        // ESCENA
        return new Scene(borderPane,1280,720);
    }

    public ArrayList<Opcion> obtenerOpciones(Pregunta pregunta) {
        ArrayList<Opcion> opciones = new ArrayList<>();
        if (this.preguntaActual.getClass() == GroupChoice.class) {
            ArrayList<Grupo> grupos= ((GroupChoice) this.preguntaActual).obtenerGrupos();
            opciones.addAll(grupos.get(0).obtenerOpciones());
        } else {
            opciones = this.preguntaActual.obtenerOpciones();
        }
        Collections.shuffle(opciones);
        return opciones;
    }

    public ArrayList<ToggleButton> obtenerBotones(ArrayList<Opcion> opciones) {
        ToggleButton temp = null;
        ArrayList<ToggleButton> botones = new ArrayList<>();
        for (Opcion opcion: opciones){
            temp = new ToggleButton(opcion.obtenerTitulo());
            temp.setPrefSize(250,100);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            //temp.setOnAction(new BotonOpcionEventHandler(opcion));
            botones.add(temp);
        }
        return botones;
    }

    public void limpiarLayouts(){

    }

}
