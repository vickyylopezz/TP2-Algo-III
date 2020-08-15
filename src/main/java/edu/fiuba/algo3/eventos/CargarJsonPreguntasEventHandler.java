package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.Lector;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
        try { lector.extraerPreguntas(archivo);
        } catch (Exception e) {
            this.mostrarAlertaDeCargaDeArchivo(e.toString());
            return;
        }

        this.preguntas.addAll(lector.obtenerPreguntas());
    }

    public void mostrarAlertaDeCargaDeArchivo(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de carga");
        alert.setHeaderText("Hubo un error al cargar las pregunas");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
