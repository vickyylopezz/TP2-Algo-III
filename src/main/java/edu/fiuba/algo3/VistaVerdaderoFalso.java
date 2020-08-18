package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

import java.util.ArrayList;

public class VistaVerdaderoFalso extends VistaPregunta{
    public VistaVerdaderoFalso(Pregunta preguntaActual, ArrayList<Opcion> opciones, ControladorEscenas controlador) {
        super(preguntaActual, opciones, controlador);

        // COMPORTAMIENTO
        for (BotonOpcionClasica boton: botones){
            boton.setOnAction(controlador.getEvento());
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

    @Override
    public void rellenarGrilla(ArrayList<Opcion> opciones) {
        ArrayList<BotonOpcionClasica> botones = obtenerBotones(opciones);

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