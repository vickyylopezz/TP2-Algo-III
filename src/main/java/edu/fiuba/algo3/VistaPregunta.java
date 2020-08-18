package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class VistaPregunta extends VBox {
    protected ControladorEscenas controlador;
    protected GridPane grid;
    protected ArrayList<BotonOpcion> seleccion = new ArrayList<>();

    protected ArrayList<BotonOpcion> botones = new ArrayList<>();

    public VistaPregunta(Pregunta pregunta, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(20);
        this.controlador = controlador;

        Text indicadorPregunta = new Text(pregunta.obtenerTitulo());
        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(50);
        rellenarGrilla(opciones);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, grid);

    }

    public ArrayList<BotonOpcion> obtenerBotones(ArrayList<Opcion> opciones) {
        //ArrayList<BotonOpcion> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            BotonOpcion boton = new BotonOpcion(opcion, 250, 100);
            botones.add(boton);
        }
        return botones;
    }

    public ArrayList<Opcion> obtenerSeleccion() {
        //System.out.println(botonesSeleccionados);
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        for (BotonOpcion boton: seleccion){
            //System.out.println(boton.obtenerOpcion().obtenerTitulo());
            opcionesSeleccionadas.add(boton.obtenerOpcion());
        }
        return opcionesSeleccionadas;
    }

    public void rellenarGrilla(ArrayList<Opcion> opciones) { }

}
