package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ListIterator;

public class VistaMultipleChoice extends VistaPregunta {
    public VistaMultipleChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);
        // Elementos del VBox
        /*Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        GridPane botones = obtenerBoton(opciones);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, botones);*/
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<ToggleButton> botones = obtenerBotones(opciones);

        for (int i = 0; i < botones.size(); i++){
            if ((i == (botones.size()-1)) && (i % 2 == 0)) {
                botones.get(i).setPrefWidth((botones.get(i).getPrefWidth())*2 + 50);
                grid.add(botones.get(i), 0, i / 2,2,1);
            } else {
                grid.add(botones.get(i), i % 2, i / 2);
            }
        }
    }

    private ArrayList<ToggleButton> obtenerBotones(ArrayList<Opcion> opciones) {
        ToggleButton temp;
        ArrayList<ToggleButton> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new ToggleButton(opcion.obtenerTitulo());
            temp.setPrefSize(250, 100);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            botones.add(temp);
        }
        return botones;
    }

}