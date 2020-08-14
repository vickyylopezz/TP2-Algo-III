package edu.fiuba.algo3.vista.escenas.preparacion;

import edu.fiuba.algo3.eventos.CargarJsonPreguntasEventHandler;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraCajaVista;
import edu.fiuba.algo3.vista.componentes.contenedores.CajaVista;
import edu.fiuba.algo3.vista.componentes.contenedores.ListadoPreguntasVista;
import edu.fiuba.algo3.vista.escenas.BaseEscena;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditorPreguntasEscena extends BaseEscena {

    public EditorPreguntasEscena(Stage stage, ArrayList<Pregunta> preguntas) {
        ObservableList<Pregunta> preguntasObserver = FXCollections.observableList(preguntas);
        //preguntasObserver.addListener(new ActualizadorArrayPreguntasListener(preguntas));

        Node cuerpo = this.crearCuerpo(stage, preguntasObserver);
        this.raiz.setCenter(cuerpo);
    }

    private Node crearCuerpo(Stage stage, ObservableList<Pregunta> preguntas) {
        StackPane cuerpo = new StackPane();
        CajaVista caja = new CajaVista();

        CabeceraCajaVista cabecera = new CabeceraCajaVista("Agrege las Preguntas");

        BotonCuadradoVista botonCargarJson = new BotonCuadradoVista("Cargar Json");
        botonCargarJson.setOnAction(new CargarJsonPreguntasEventHandler(stage, preguntas));
        cabecera.agregarADerecha(botonCargarJson);

        ListadoPreguntasVista listadoPreguntas = new ListadoPreguntasVista(preguntas);

        caja.setTop(cabecera);
        caja.setCenter(listadoPreguntas);

        StackPane.setMargin(caja, new Insets(20));
        cuerpo.getChildren().add(caja);

        return cuerpo;
    }
}
