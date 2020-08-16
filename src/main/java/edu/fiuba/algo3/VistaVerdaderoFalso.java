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
import java.util.ListIterator;

public class VistaVerdaderoFalso extends VistaPregunta{
    public VistaVerdaderoFalso(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);
        // Elementos del VBox
        /*Text indicadorPregunta = new Text(preguntaActual.obtenerTitulo());
        ArrayList<Button> botones = obtenerBotones(opciones);
        GridPane grid = ordenarBotones(botones);
        //Button confirmar = new Button("Confirmar");

        this.setAlignment(Pos.CENTER);
        //this.setMargin(confirmar, new Insets(100,0,0,0));
        this.getChildren().addAll(indicadorPregunta, grid);*/

        // Comportamiento de confirmar?
    }

    private ArrayList<Button> obtenerBotones(ArrayList<Opcion> opciones) {
        Button temp;
        ArrayList<Button> botones = new ArrayList<>();
        for (Opcion opcion : opciones) {
            temp = new Button(opcion.obtenerTitulo());
            temp.setPrefSize(250, 100);
            temp.setAlignment(Pos.CENTER);
            temp.setWrapText(true);
            temp.setOnAction(new BotonConfirmarEventHandler(this.controlador));
            botones.add(temp);
        }
        return botones;
    }

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<Button> botones = obtenerBotones(opciones);

        for (int i = 0; i < botones.size(); i++){
            if ((i == (botones.size()-1)) && (i % 2 == 0)) {
                botones.get(i).setPrefWidth((botones.get(i).getPrefWidth())*2 + 20);
                grid.add(botones.get(i), 0, i / 2,2,1);
            } else {
                grid.add(botones.get(i), i % 2, i / 2);
            }
        }
    }

}