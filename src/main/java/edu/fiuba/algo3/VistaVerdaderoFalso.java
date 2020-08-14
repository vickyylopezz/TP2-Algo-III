package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ListIterator;

public class VistaVerdaderoFalso extends VBox{
    public VistaVerdaderoFalso(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(20);
        // Elementos del VBox
        Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        ArrayList<Button> botones = obtenerBotones(opciones);
        botones.forEach(boton -> {
            boton.setOnAction(e -> {
                controlador.actualizarParametros();
                new Intermission(controlador.getStage(), controlador);
            });
        });
        GridPane grid = ordenarBotones(botones);
        //Button confirmar = new Button("Confirmar");

        this.setAlignment(Pos.CENTER);
        //this.setMargin(confirmar, new Insets(100,0,0,0));
        this.getChildren().addAll(indicadorPregunta, grid);

        // Comportamiento de confirmar?
    }

    public ArrayList<Button> obtenerBotones(ArrayList<Opcion> opciones) {
        Button opcion1 = new Button(opciones.get(0).obtenerTitulo());
        Button opcion2 = new Button(opciones.get(1).obtenerTitulo());

        ArrayList<Button> botones = new ArrayList<Button>();
        botones.add(opcion1); botones.add(opcion2);

        return botones;
    }

    public GridPane ordenarBotones(ArrayList<Button> botones) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.add(botones.get(0), 0, 0);
        gridPane.add(botones.get(1),1,0);

        return gridPane;
    }

}