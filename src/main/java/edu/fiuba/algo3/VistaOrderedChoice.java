package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class VistaOrderedChoice extends VistaPregunta {
    private int numeroClic = 0;
    private Button primero, segundo;

    public VistaOrderedChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        seleccion.addAll(botones);
        for (BotonOpcion boton: botones){
            boton.setPrefSize(500,50);
            boton.setOnAction(e -> botonPresionado(e.getSource()));
        }
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = obtenerBotones(opciones);

        int i = 0;
        for (Button boton: botones) {
            grid.add(new Label(String.valueOf(i+1)),0, i);
            grid.add(boton,1, i);
            i++;
        }
    }

    /*public ArrayList<BotonOpcion> obtenerBotones(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            BotonOpcion boton = new BotonOpcion(opcion, 500, 50);

            botones.add(boton);
        }
        //seleccion.addAll(botones);
        return botones;
    }*/

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
