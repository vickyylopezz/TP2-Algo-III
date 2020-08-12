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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private ArrayList<Button> botones;
    private ListIterator<Pregunta> iterador;

    public ConstructorEscenas(ArrayList<Jugador> jugadores, ArrayList<Pregunta> preguntas) {
        this.jugadores = jugadores;

        Collections.shuffle(preguntas);
        this.iterador = preguntas.listIterator();
        this.preguntaActual = this.iterador.next();

        this.botones = obtenerBotones(obtenerOpciones(this.preguntaActual));
    }

    public Scene crearEscena() {
        // JUGADOR ACTUAL + PUNTAJE
        Text indicadorJugador = new Text("Jugador 1: " + jugadores.get(0).nombre());
        indicadorJugador.setFont(new Font(15));
        Text indicadorPuntaje = new Text("Puntos: " + jugadores.get(0).puntajeTotal());
        indicadorPuntaje.setFont(new Font(15));
        HBox indicadorJugadores = new HBox(200, indicadorJugador, indicadorPuntaje);
        indicadorJugadores.setAlignment(Pos.TOP_LEFT);

        // PREGUNTA
        Text indicadorPregunta = new Text(this.preguntaActual.obtenerTitulo());

        // OPCIONES
        ArrayList<Opcion> opciones = obtenerOpciones(this.preguntaActual);
        // BOTONES PARA LAS OPCIONES
        botones = obtenerBotones(opciones);

        // LAYOUTS HBOX
        HBox hbox1 = new HBox(20);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(20);
        hbox2.setAlignment(Pos.CENTER);

        // LAYOUT VBOX
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);


        Button confirmar = new Button("Confirmar");
        confirmar.setOnAction(e -> {
            if (iterador.hasNext()) {
                vbox.getChildren().clear();
                hbox1.getChildren().clear();
                hbox2.getChildren().clear();

                this.preguntaActual = iterador.next();

                indicadorPregunta.setText(this.preguntaActual.obtenerTitulo());
                vbox.getChildren().add(indicadorPregunta);

                botones = obtenerBotones(obtenerOpciones(this.preguntaActual));
                for (int i = 0; i < this.botones.size(); i++){
                    if (i < 3) {
                        hbox1.getChildren().add(this.botones.get(i));
                    } else {
                        hbox2.getChildren().add(this.botones.get(i));
                    }
                }

                vbox.getChildren().addAll(hbox1,hbox2);
                vbox.getChildren().add(confirmar);

            } else {
                System.exit(0);
            }
        });

        vbox.getChildren().add(indicadorPregunta);
        for (int i = 0; i < this.botones.size(); i++){
            if (i < 3) {
                hbox1.getChildren().add(this.botones.get(i));
            } else {
                hbox2.getChildren().add(this.botones.get(i));
            }
        }
        vbox.getChildren().addAll(hbox1,hbox2);
        vbox.getChildren().add(confirmar);

        // ESCENA
        return new Scene(vbox,800,600);
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

    public ArrayList<Button> obtenerBotones(ArrayList<Opcion> opciones) {
        Button temp = null;
        ArrayList<Button> botones = new ArrayList<>();
        for (Opcion opcion: opciones){
            temp = new Button(opcion.obtenerTitulo());
            temp.setPrefSize(220,70);
            temp.setWrapText(true);
            temp.setOnAction(new BotonOpcionEventHandler(opcion));
            botones.add(temp);
        }
        return botones;
    }

    /*public VBox crearLayout(Text indicadorPregunta, ArrayList<Button> botones) {

        HBox hbox1 = new HBox(20);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(20);
        hbox2.setAlignment(Pos.CENTER);

        for (int i = 0; i < botones.size(); i++){
            if (i < 3) {
                hbox1.getChildren().add(botones.get(i));
            } else {
                hbox2.getChildren().add(botones.get(i));
            }
        }

        VBox vbox = new VBox(20, indicadorPregunta, hbox1, hbox2);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }*/


}
