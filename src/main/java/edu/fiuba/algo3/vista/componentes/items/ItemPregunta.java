package edu.fiuba.algo3.vista.componentes.items;

import edu.fiuba.algo3.eventos.BorrarPreguntaEventHanlder;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
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
        MiniTexto tituloPregunta = new MiniTexto(pregunta.obtenerTitulo());
        tituloPregunta.setWrapText(true);

        Pane separador = new Pane();
        HBox.setHgrow(separador, Priority.ALWAYS);

        BotonCuadradoVista botonBorrar = new BotonCuadradoVista("borrar");
        botonBorrar.setWrapText(false);
        botonBorrar.setMinWidth(55);
        botonBorrar.setOnAction(new BorrarPreguntaEventHanlder(preguntas, pregunta));

        this.getChildren().addAll(tituloPregunta, separador, botonBorrar);
    }
}
