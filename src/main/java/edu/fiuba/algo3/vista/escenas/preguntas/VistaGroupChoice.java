package edu.fiuba.algo3.vista.escenas.preguntas;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.botones.BotonCircularVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionGroup;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorEscenas;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class VistaGroupChoice extends VistaPregunta {
    private BotonOpcionGroup botonActivo;
    private Button izquierda, derecha;
    private ArrayList<Grupo> grupos;

    private ArrayList<BotonOpcionGroup> seleccionIzquierda = new ArrayList<>();
    private ArrayList<BotonOpcionGroup> seleccionDerecha = new ArrayList<>();

    public VistaGroupChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador){
        super(preguntaActual, opciones, controlador);

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

        //grid.setGridLinesVisible(true);
        //grid.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcionGroup> botones = obtenerBotonesGroup(opciones);

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

    private ArrayList<BotonOpcionGroup> obtenerBotonesGroup(ArrayList<Opcion> opciones){
        ArrayList<BotonOpcionGroup> botones = new ArrayList<>();

        for (int i=0; i < opciones.size(); i = i+2) {
            BotonOpcionGroup boton = new BotonOpcionGroup(opciones.get(i), opciones.get(i+1),300, 50);
            boton.setOnAction(e -> {
                this.izquierda.setDisable(false);
                this.derecha.setDisable(false);
                opcionActivada(e.getSource());
            });
            botones.add(boton);
        }
        Collections.shuffle(botones);
        return botones;
    }


    private void opcionActivada(Object source) {
        BotonOpcionGroup boton  = (BotonOpcionGroup)source;
        if (GridPane.getColumnIndex(boton) != 1){
            grid.getChildren().remove(boton);
            grid.add(boton, 1, GridPane.getRowIndex(boton));
            seleccionIzquierda.remove(boton);
            seleccionDerecha.remove(boton);
            //System.out.println(botonesSeleccionados);
        }
        botonActivo = boton;
    }

    public HBox obtenerControles(){
        HBox controles = new HBox(50);
        controles.setAlignment(Pos.CENTER);

        Image izq = CargadorResources.obtenerImagen(Resources.IconoFlechaIzquierdaRuta());
        ImageView perfilIzquierdo = new ImageView(izq);
        Image der = CargadorResources.obtenerImagen(Resources.IconoFlechaDerechaRuta());
        ImageView perfilDerecho = new ImageView(der);

        izquierda = new BotonCircularVista(perfilIzquierdo);
        izquierda.setDisable(true);
        izquierda.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int filaBoton = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 0, filaBoton);
            grid.requestFocus();

            seleccionIzquierda.add(botonActivo);
        });


        derecha = new BotonCircularVista(perfilDerecho);
        derecha.setDisable(true);
        derecha.setOnAction(e -> {
            izquierda.setDisable(true);
            derecha.setDisable(true);

            int firstRow = GridPane.getRowIndex(botonActivo);
            grid.getChildren().remove(botonActivo);
            grid.add(botonActivo, 2, firstRow);
            grid.requestFocus();

            seleccionDerecha.add(botonActivo);
        });

        controles.getChildren().addAll(izquierda, derecha);
        return controles;
    }

    @Override
    public ArrayList<Opcion> obtenerSeleccion(){
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();

        for (BotonOpcionGroup boton: seleccionIzquierda) {
            if (boton.obtenerOpcionCorrecta().agrupacion() == grupos.get(0)) {
                opcionesSeleccionadas.add(boton.obtenerOpcionCorrecta());
            } else {
                opcionesSeleccionadas.add(boton.obtenerOpcionIncorrecta());
            }
        }
        for (BotonOpcionGroup boton: seleccionDerecha) {
            if (boton.obtenerOpcionCorrecta().agrupacion() == grupos.get(1)) {
                opcionesSeleccionadas.add(boton.obtenerOpcionCorrecta());
            } else {
                opcionesSeleccionadas.add(boton.obtenerOpcionIncorrecta());
            }
        }
        return opcionesSeleccionadas;
    }

}
