package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class VistaGroupChoice extends VistaPregunta {
    private Button botonActivo;
    private Button izquierda, derecha;

    public VistaGroupChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador){
        super(preguntaActual, opciones, controlador);

        /*Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        grid = obtenerGrilla(opciones);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, grid);*/
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<Button> botones = obtenerBotones(opciones);

        ColumnConstraints col1 = new ColumnConstraints(300);
        ColumnConstraints col2 = new ColumnConstraints(300);
        ColumnConstraints col3 = new ColumnConstraints(300);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        int i = 1;
        for (Button boton: botones) {
            grid.add(boton,1, i);
            i++;
        }

        Label grupo1 = new Label("Grupo 1");
        GridPane.setHalignment(grupo1, HPos.CENTER);
        Label grupo2 = new Label("Grupo 2");
        GridPane.setHalignment(grupo2, HPos.CENTER);

        HBox controles = obtenerControles();
        GridPane.setHalignment(controles, HPos.CENTER);

        grid.add(grupo1,0,0);
        grid.add(controles,1,0);
        grid.add(grupo2,2,0);

        grid.setGridLinesVisible(true);
    }

    public ArrayList<Button> obtenerBotones(ArrayList<Opcion> opciones) {
        Button temp = null;
        ArrayList<Button> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new Button(opcion.obtenerTitulo());
            temp.setPrefSize(300, 50);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);

            temp.setOnAction(e -> opcionActivada(e.getSource()));
            temp.setOnAction(e -> {
                this.izquierda.setDisable(false);
                this.derecha.setDisable(false);
                opcionActivada(e.getSource());
            });
            botones.add(temp);
        }
        return botones;
    }

    private void opcionActivada(Object source) {
        Button boton  = (Button)source;
        if (GridPane.getColumnIndex(boton) != 1){
            grid.getChildren().remove(boton);
            grid.add(boton, 1, GridPane.getRowIndex(boton));
        }
        botonActivo = boton;
    }

    public HBox obtenerControles(){
        HBox controles = new HBox(20);

        izquierda = new Button("<");
        izquierda.setPrefSize(100,50);
        izquierda.setDisable(true);

        derecha = new Button(">");
        derecha.setPrefSize(100,50);
        derecha.setDisable(true);

        izquierda.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int firstRow = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 0, firstRow);
            grid.requestFocus();
        });
        derecha.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int firstRow = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 2, firstRow);
            grid.requestFocus();
        });

        controles.getChildren().addAll(izquierda, derecha);
        controles.setAlignment(Pos.CENTER);
        return controles;
        }
    }
