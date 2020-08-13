package edu.fiuba.algo3;

import edu.fiuba.algo3.eventos.BotonOpcionEventHandler;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionGroupChoice;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class ConstructorEscenas {
    private ArrayList<Jugador> jugadores;
    private Pregunta preguntaActual;
    private ArrayList<ToggleButton> botones;
    private ListIterator<Pregunta> iterador;

    public ConstructorEscenas(ArrayList<Jugador> jugadores, ArrayList<Pregunta> preguntas) {
        this.jugadores = jugadores;

        Collections.shuffle(preguntas);
        this.iterador = preguntas.listIterator();
        this.preguntaActual = this.iterador.next();

        this.botones = obtenerBotones(obtenerOpciones(this.preguntaActual));
    }

    /*public seleccionarEscena() {
        if (preguntaActual.getClass() == OrderedChoice.class) {
            crearEscenaOrdered();
        } else if (preguntaActual.getClass() == GroupChoice.class) {
            crearEscenaGroup();
        } else if (preguntaActual.getClass() == VerdaderoFalsoClasico.class || preguntaActual.getClass() == VerdaderoFalsoConPenalidad.class) {
            crearEscenaVF();
        } else {
            crearEscena();
        }

    }*/

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
        botones = obtenerBotones(opciones);

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

                botones = obtenerBotones(obtenerOpciones(this.preguntaActual));
                for (int i = 0; i < this.botones.size(); i++){
                    if ((i == (this.botones.size()-1)) && (i % 2 == 0)) {
                        this.botones.get(i).setPrefWidth((this.botones.get(i).getPrefWidth())*2);
                        gridPane.add(this.botones.get(i), 0, i / 2,2,1);
                    } else {
                        gridPane.add(this.botones.get(i), i % 2, i / 2);
                    }
                }
                vbox.getChildren().add(gridPane);

            } else {
                System.exit(0);
            }
        });

        vbox.getChildren().add(indicadorPregunta);
        //vbox.getChildren().add(confirmar);

        for (int i = 0; i < this.botones.size(); i++){
            gridPane.add(this.botones.get(i), i%2, i/2);
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

}
