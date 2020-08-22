package edu.fiuba.algo3.eventos.preparacion.editorPreguntas;

import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.Lector;
import edu.fiuba.algo3.vista.Resources;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
        if (archivo == null) return;

        ArrayList<Pregunta> preguntasNuevas;

        Lector lector = new Lector();
        try { preguntasNuevas = lector.extraerPreguntas(archivo);
        } catch (LectorError | PreguntaError error) {
            this.mostrarAlertaDeCargaDeArchivo(error.getMessage());
            return;
        }

        this.preguntas.addAll(preguntasNuevas);
        Collections.shuffle(this.preguntas);
    }

    public void mostrarAlertaDeCargaDeArchivo(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al cargar Preguntas");
        alert.setHeaderText("Hubo un error al cargar las pregunas");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
