package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class VistaOrderedChoice extends VBox {
    private int clickCounter = 0;
    private Button first, second;
    private GridPane columna;

    public VistaOrderedChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones) {
        super(20);
        // Elementos del VBox
        Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        this.columna = obtenerBotones(opciones);
        //Button confirmar = new Button("Confirmar");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, columna);
    }

    public GridPane obtenerBotones(ArrayList<Opcion> opciones) {
        Button temp = null;
        ArrayList<Button> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new Button(opcion.obtenerTitulo());
            temp.setPrefSize(500, 50);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            temp.setOnAction(e -> buttonCliked(e.getSource()));
            //temp.setOnAction(new BotonOpcionEventHandler(opcion));
            botones.add(temp);
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(50);
        int i = 0;
        for (Button boton: botones) {
            grid.add(new Label(String.valueOf(i+1)),0,i);
            grid.add(boton,1,i);
            i++;
        }

        return grid;
    }

    private void buttonCliked(Object source) {

        if(!(source instanceof Button)) return;
        Button button  = (Button)source;

        if (clickCounter == 0) {
            first = button;
        } else {
            second = button;
            swap();
        }

        //System.out.println(clickCounter + " " + ((Button)source).getText()    );
        clickCounter=  ++clickCounter %2 ;  // changes values between 0 1
    }

    private void swap() {
        int firstRow = GridPane.getRowIndex(first);
        int secondRow = GridPane.getRowIndex(second);

        if (firstRow != secondRow) {
            columna.getChildren().removeAll(first, second);
            columna.add(first, 1, secondRow);
            columna.add(second, 1, firstRow);
        }
        first.requestFocus();
    }
}
