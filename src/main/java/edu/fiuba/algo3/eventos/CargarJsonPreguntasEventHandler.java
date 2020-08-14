package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.Lector;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CargarJsonPreguntasEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private final ObservableList<Pregunta> preguntas;

    public CargarJsonPreguntasEventHandler(Stage stage, ObservableList<Pregunta> preguntas) {
        this.stage = stage;
        this.preguntas = preguntas;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        FileChooser seleccionadorArchivos = new FileChooser();
        seleccionadorArchivos.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Json Archivo", "*.json")
        );
        File archivo = seleccionadorArchivos.showOpenDialog(this.stage);

        Lector lector = new Lector();
        try {
            lector.extraerPreguntas(archivo);
        } catch (IOException | PreguntaError e) {
            e.printStackTrace();
        }

        this.preguntas.addAll(lector.obtenerPreguntas());
    }
}
