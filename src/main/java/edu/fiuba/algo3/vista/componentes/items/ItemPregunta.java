package edu.fiuba.algo3.vista.componentes.items;

import edu.fiuba.algo3.eventos.BajarPreguntaEventHandler;
import edu.fiuba.algo3.eventos.BorrarPreguntaEventHanlder;
import edu.fiuba.algo3.eventos.SubirPreguntaEventHandler;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.preguntas.PreguntaBarraVista;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ItemPregunta extends Item {

    public ItemPregunta(ObservableList<Pregunta> preguntas, Pregunta pregunta) {
        super();

        this.crearEstructura(preguntas, pregunta);
    }

    private void crearEstructura(ObservableList<Pregunta> preguntas, Pregunta pregunta) {
        BotonCuadradoVista botonSubir = new BotonCuadradoVista("subir");
        botonSubir.setOnAction(new SubirPreguntaEventHandler(preguntas, pregunta));

        BotonCuadradoVista botonBajar = new BotonCuadradoVista("bajar");
        botonBajar.setOnAction(new BajarPreguntaEventHandler(preguntas, pregunta));

        PreguntaBarraVista barraPregunta = new PreguntaBarraVista(pregunta);

        Pane separador = new Pane();
        HBox.setHgrow(separador, Priority.ALWAYS);

        BotonCuadradoVista botonBorrar = new BotonCuadradoVista("borrar");
        botonBorrar.setOnAction(new BorrarPreguntaEventHanlder(preguntas, pregunta));

        this.getChildren().addAll(botonSubir, botonBajar, barraPregunta, separador, botonBorrar);
    }
}
