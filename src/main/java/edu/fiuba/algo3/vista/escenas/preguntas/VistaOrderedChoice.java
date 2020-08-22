package edu.fiuba.algo3.vista.escenas.preguntas;

import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorVistaJuego;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class VistaOrderedChoice extends VistaPregunta {
    private int numeroClic = 0;
    private Button primero, segundo;

    public VistaOrderedChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorVistaJuego controlador) {
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        seleccion.addAll(botones);
        for (BotonOpcionClasica boton: botones){
            boton.setPrefSize(500,50);
            boton.setOnAction(e -> botonPresionado(e.getSource()));
        }

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(200,50);
        confirmar.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 18px");
        confirmar.setOnAction(controlador.getEvento());
        this.setBottom(confirmar);
        BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);

        this.grid.setVgap(40);
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcionClasica> botones = obtenerBotones(opciones, 550, 200);

        int i = 0;
        for (Button boton: botones) {
            Label posicion = new Label(String.valueOf(i+1));
            posicion.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bolder");
            grid.add(posicion,0, i);
            grid.add(boton,1, i);
            i++;
        }
    }

    private void botonPresionado(Object source) {
        //if(!(source instanceof Button)) return;
        Button button  = (Button)source;

        if (numeroClic == 0) {
            primero = button;
        } else {
            segundo = button;
            swap();
            //System.out.println(botonesSeleccionados);
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

            Collections.swap(seleccion, primeraFila, segundaFila);
        }
        grid.requestFocus();
    }

}
