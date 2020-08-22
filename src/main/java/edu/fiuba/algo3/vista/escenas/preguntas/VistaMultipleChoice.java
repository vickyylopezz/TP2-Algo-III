package edu.fiuba.algo3.vista.escenas.preguntas;

import edu.fiuba.algo3.vista.componentes.botones.BotonOpcionClasica;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.vista.escenas.controlador.ControladorVistaJuego;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class VistaMultipleChoice extends VistaPregunta {
    public VistaMultipleChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorVistaJuego controlador) {
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        for (BotonOpcionClasica boton: botones){
            boton.setOnAction(e -> {
                if (seleccion.contains(boton)){
                    boton.setStyle("-fx-font-size: 18px");
                    seleccion.remove(boton);
                } else {
                    boton.setStyle("-fx-background-color: lightskyblue; -fx-font-size: 18px;");
                    seleccion.add(boton);
                }
            });
        }

        Button confirmar = new Button("Confirmar");
        confirmar.setPrefSize(200,50);
        confirmar.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 18px");
        confirmar.setOnAction(controlador.getEvento());
        this.setBottom(confirmar);
        BorderPane.setAlignment(confirmar, Pos.BOTTOM_CENTER);
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcionClasica> botones = obtenerBotones(opciones, 400, 150);

        for (int i = 0; i < botones.size(); i++){
            if ((i == (botones.size()-1)) && (i % 2 == 0)) {
                botones.get(i).setPrefWidth((botones.get(i).getPrefWidth())*2 + 50);
                grid.add(botones.get(i), 0, i / 2,2,1);
            } else {
                grid.add(botones.get(i), i % 2, i / 2);
            }
        }
    }
}