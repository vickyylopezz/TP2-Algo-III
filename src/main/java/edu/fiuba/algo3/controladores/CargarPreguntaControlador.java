package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.lector.Lector;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.escenas.prejuego.CargarPreguntasVista;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CargarPreguntaControlador {

    private final CargarPreguntasVista vista;
    private final ArrayList<Pregunta> preguntas;
    private final Stage stage;

    public CargarPreguntaControlador(Stage stage) {
        this.vista = new CargarPreguntasVista(this);
        this.preguntas = new ArrayList<>();
        this.stage = stage;
        this.stage.setScene(this.vista.obtenerEscena());
    }

    public void mostrarVista() { this.stage.show(); }

    public void cargarJson() {
        FileChooser seleccionadorArchivos = new FileChooser();
        seleccionadorArchivos.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Json files", "*.json")
        );

        File archivo = seleccionadorArchivos.showOpenDialog(this.stage);

        Lector lector = new Lector();
        try { lector.extraerPreguntas(archivo); }
        catch (IOException | PreguntaError e) { e.printStackTrace(); }

        this.preguntas.addAll(lector.obtenerPreguntas());

        this.vista.actualizarPreguntas(this.preguntas);
    }

    public void subirPregunta(Pregunta pregunta) {
        int indiceAMover = this.preguntas.indexOf(pregunta) - 1;
        if (indiceAMover < 0) return;

        this.preguntas.remove(pregunta);
        this.preguntas.add(indiceAMover, pregunta);

        this.vista.actualizarPreguntas(this.preguntas);
    }

    public void bajarPregunta(Pregunta pregunta) {
        int indiceAMover = this.preguntas.indexOf(pregunta) + 1;
        if (indiceAMover == this.preguntas.size()) return;

        this.preguntas.remove(pregunta);
        this.preguntas.add(indiceAMover, pregunta);

        this.vista.actualizarPreguntas(this.preguntas);
    }

    public void borrarPregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
        this.vista.actualizarPreguntas(this.preguntas);
    }

    public ArrayList<Pregunta> obtenerPreguntas() { return new ArrayList<>(this.preguntas); }
}
