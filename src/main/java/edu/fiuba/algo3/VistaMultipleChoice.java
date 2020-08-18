package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import java.util.ArrayList;

public class VistaMultipleChoice extends VistaPregunta {
    public VistaMultipleChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        for (BotonOpcion boton: botones){
            boton.setOnAction(e -> {
                if (seleccion.contains(boton)){
                    boton.setStyle(null);
                    seleccion.remove(boton);
                } else {
                    boton.setStyle("-fx-background-color: lightskyblue");
                    seleccion.add(boton);
                }
            });
        }
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = obtenerBotones(opciones);

        for (int i = 0; i < botones.size(); i++){
            if ((i == (botones.size()-1)) && (i % 2 == 0)) {
                botones.get(i).setPrefWidth((botones.get(i).getPrefWidth())*2 + 50);
                grid.add(botones.get(i), 0, i / 2,2,1);
            } else {
                grid.add(botones.get(i), i % 2, i / 2);
            }
        }
    }

    /*private ArrayList<BotonOpcion> obtenerBotones(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcion> botones = new ArrayList<>();

        for (Opcion opcion : opciones) {
            BotonOpcion boton = new BotonOpcion(opcion, 250, 100);

            botones.add(boton);
        }
        return botones;
    }*/

}