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

public class VistaVerdaderoFalso extends VBox{
    public VistaVerdaderoFalso(Pregunta preguntaActual, ArrayList<Opcion> opciones) {
        super(20);
        // Elementos del VBox
        Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        GridPane botones = obtenerBotones(opciones);
        //Button confirmar = new Button("Confirmar");

        this.setAlignment(Pos.CENTER);
        //this.setMargin(confirmar, new Insets(100,0,0,0));
        this.getChildren().addAll(indicadorPregunta, botones);

        // Comportamiento de confirmar?
    }

    public GridPane obtenerBotones(ArrayList<Opcion> opciones) {
        Button opcion1 = new Button(opciones.get(0).obtenerTitulo());
        Button opcion2 = new Button(opciones.get(1).obtenerTitulo());

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.add(opcion1, 0, 0);
        gridPane.add(opcion2,1,0);

        // condicion de botones
        /*opcion1.setOnAction(e -> {
            if (iterador.hasNext()) {
                this.preguntaActual = iterador.next();
                crearUnaEscena();
            }
        });*/
        return gridPane;
    }

}