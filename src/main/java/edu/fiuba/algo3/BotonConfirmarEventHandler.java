package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BotonConfirmarEventHandler implements EventHandler<ActionEvent>{
    private ControladorEscenas controlador;
    private Stage stage;
    private VistaPregunta vista;

    public BotonConfirmarEventHandler(ControladorEscenas controlador, VistaPregunta vista) {
        this.controlador = controlador;
        this.stage = controlador.getStage();
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // la funcion del iterador la resuelve la jugada
        // tomo los botones seleccionados de la vista
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        if (actionEvent.getSource() instanceof BotonOpcionClasica){
            //Verdadero o Falso
            opcionesSeleccionadas.add(((BotonOpcionClasica) actionEvent.getSource()).obtenerOpcion());
        } else {
            opcionesSeleccionadas = vista.obtenerSeleccion();
        }
        // DEBUG: muestro las opciones
        for (Opcion opcion: opcionesSeleccionadas){
            System.out.println(opcion.obtenerTitulo());
        }
        // TO-DO: a esta altura ya habría que mandarle las opciones a la jugada, respuesta o quien sea

        // crucial, avanzo el juego y los atributos. jugada no hará algo parecido?
        controlador.actualizarAtributos();

        // la funcion del cambio de escena la resolvería otra clase, pero por el mientras...
        new Intermission(controlador);
    }
}
