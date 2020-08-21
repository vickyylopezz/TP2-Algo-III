package edu.fiuba.algo3.vista.escenas.preguntas;

import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorEscenas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;

public abstract class VistaPregunta extends BorderPane {
    protected ControladorEscenas controlador;
    protected GridPane grid;
    protected ArrayList<BotonOpcionClasica> seleccion = new ArrayList<>();

    protected ArrayList<BotonOpcionClasica> botones = new ArrayList<>();

    public VistaPregunta(Pregunta pregunta, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super();
        this.controlador = controlador;

        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(50);
        rellenarGrilla(opciones);

        this.setPadding(new Insets(25,25,25,25));
        this.setCenter(grid);
    }

    public ArrayList<BotonOpcionClasica> obtenerBotones(ArrayList<Opcion> opciones, int ancho, int altura) {
        //ArrayList<BotonOpcion> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            BotonOpcionClasica boton = new BotonOpcionClasica(opcion, ancho, altura);
            botones.add(boton);
        }
        Collections.shuffle(botones);
        return botones;
    }

    public ArrayList<Opcion> obtenerSeleccion() {
        //System.out.println(botonesSeleccionados);
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        for (BotonOpcionClasica boton: seleccion){
            //System.out.println(boton.obtenerOpcion().obtenerTitulo());
            opcionesSeleccionadas.add(boton.obtenerOpcion());
        }
        return opcionesSeleccionadas;
    }

    public void rellenarGrilla(ArrayList<Opcion> opciones) { }

}
