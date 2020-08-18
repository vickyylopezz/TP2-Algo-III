package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionGroupChoice;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class VistaGroupChoice extends VistaPregunta {
    private BotonOpcion botonActivo;
    private Button izquierda, derecha;
    private ArrayList<Grupo> grupos;

    public VistaGroupChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador){
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        for (BotonOpcion boton: botones){
            boton.setPrefSize(300,50);
            boton.setOnAction(e -> {
                this.izquierda.setDisable(false);
                this.derecha.setDisable(false);
                opcionActivada(e.getSource());
            });
        }

        this.grupos = ((GroupChoice)preguntaActual).obtenerGrupos();
        Label grupo1 = new Label(grupos.get(0).obtenerTitulo());
        GridPane.setHalignment(grupo1, HPos.CENTER);
        Label grupo2 = new Label(grupos.get(1).obtenerTitulo());
        GridPane.setHalignment(grupo2, HPos.CENTER);

        HBox controles = obtenerControles();
        GridPane.setHalignment(controles, HPos.CENTER);

        grid.add(grupo1,0,0);
        grid.add(controles,1,0);
        grid.add(grupo2,2,0);

        grid.setGridLinesVisible(true);
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = obtenerBotones(opciones);

        ColumnConstraints col1 = new ColumnConstraints(300);
        ColumnConstraints col2 = new ColumnConstraints(300);
        ColumnConstraints col3 = new ColumnConstraints(300);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        int i = 1;
        for (Button boton: botones) {
            grid.add(boton,1, i);
            i++;
        }




    }

    /*public ArrayList<BotonOpcion> obtenerBotones(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            BotonOpcion boton = new BotonOpcion(opcion, 300, 50);

            botones.add(boton);
        }
        return botones;
    }*/

    private void opcionActivada(Object source) {
        BotonOpcion boton  = (BotonOpcion)source;
        if (GridPane.getColumnIndex(boton) != 1){
            grid.getChildren().remove(boton);
            grid.add(boton, 1, GridPane.getRowIndex(boton));
            seleccion.remove(boton);
            //System.out.println(botonesSeleccionados);
        }
        botonActivo = boton;
    }

    public HBox obtenerControles(){
        HBox controles = new HBox(20);
        controles.setAlignment(Pos.CENTER);

        for (int i=0; i<2; i++){
            Button boton = new Button();
            boton.setPrefSize(100,50);
            boton.setDisable(true);
            controles.getChildren().add(boton);

            int columnaDestino = i;
            boton.setOnAction(e -> {
                izquierda.setDisable(true);
                derecha.setDisable(true);

                int firstRow = GridPane.getRowIndex(botonActivo);
                grid.getChildren().remove(botonActivo);
                grid.add(botonActivo, columnaDestino*2, firstRow);
                grid.requestFocus();

                seleccion.add(botonActivo);
            });
        }
        izquierda = (Button)controles.getChildren().get(0);
        izquierda.setText("<");

        derecha = (Button)controles.getChildren().get(1);
        derecha.setText(">");

        /*izquierda.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int firstRow = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 0, firstRow);
            grid.requestFocus();

            seleccion.add(botonActivo);
            //System.out.println(botonesSeleccionados);
        });
        derecha.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int firstRow = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 2, firstRow);
            grid.requestFocus();

            seleccion.add(botonActivo);
            //System.out.println(botonesSeleccionados);
        });*/
        return controles;
    }

    @Override
    public ArrayList<Opcion> obtenerSeleccion(){
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        for (BotonOpcion boton: seleccion) {
            System.out.println(((OpcionGroupChoice)boton.obtenerOpcion()).obtenerGrupo());
        }
        return opcionesSeleccionadas;
    }

}
