package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class VistaPregunta extends VBox {
    protected ControladorEscenas controlador;
    protected GridPane grid;

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

    public void rellenarGrilla(ArrayList<Opcion> opciones) { }

}
