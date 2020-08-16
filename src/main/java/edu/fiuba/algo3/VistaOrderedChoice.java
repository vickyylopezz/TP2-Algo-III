package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class VistaOrderedChoice extends VistaPregunta {
    private int numeroClic = 0;
    private Button primero, segundo;

    public VistaOrderedChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);
        // Elementos del VBox
        /*Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        this.columna = obtenerBotones(opciones);
        //Button confirmar = new Button("Confirmar");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, columna);*/
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<Button> botones = obtenerBotones(opciones);

        int i = 0;
        for (Button boton: botones) {
            grid.add(new Label(String.valueOf(i+1)),0, i);
            grid.add(boton,1, i);
            i++;
        }
    }

    public ArrayList<Button> obtenerBotones(ArrayList<Opcion> opciones) {
        Button temp = null;
        ArrayList<Button> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new Button(opcion.obtenerTitulo());
            temp.setPrefSize(500, 50);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            temp.setOnAction(e -> botonPresionado(e.getSource()));
            botones.add(temp);
        }
        return botones;
    }

    private void botonPresionado(Object source) {
        //if(!(source instanceof Button)) return;
        Button button  = (Button)source;

        if (numeroClic == 0) {
            primero = button;
        } else {
            segundo = button;
            swap();
        }
        numeroClic = (++numeroClic) % 2; // 0-1
    }

    private void swap() {
        int primeraFila = GridPane.getRowIndex(primero);
        int segundaFila = GridPane.getRowIndex(segundo);

        if (primeraFila != segundaFila) {
            grid.getChildren().removeAll(primero, segundo);
            grid.add(primero, 1, segundaFila);
            grid.add(segundo, 1, primeraFila);
        }
        grid.requestFocus();
    }
}
