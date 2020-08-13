package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ListIterator;

public class VistaMultipleChoice extends VBox {
    public VistaMultipleChoice(Pregunta preguntaActual, ArrayList<Opcion> opciones) {
        super(20);
        // Elementos del VBox
        Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        GridPane botones = obtenerBotones(opciones);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(indicadorPregunta, botones);
    }

    public GridPane obtenerBotones(ArrayList<Opcion> opciones) {
        ToggleButton temp = null;
        ArrayList<ToggleButton> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new ToggleButton(opcion.obtenerTitulo());
            temp.setPrefSize(250, 100);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            //temp.setOnAction(new BotonOpcionEventHandler(opcion));
            botones.add(temp);
        }

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        for (int i = 0; i < botones.size(); i++){
            if ((i == (botones.size()-1)) && (i % 2 == 0)) {
                botones.get(i).setPrefWidth((botones.get(i).getPrefWidth())*2 + 20);
                gridPane.add(botones.get(i), 0, i / 2,2,1);
            } else {
                gridPane.add(botones.get(i), i % 2, i / 2);
            }
        }
        return gridPane;
    }

}