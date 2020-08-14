package edu.fiuba.algo3.vista.componentes.contenedores;

import edu.fiuba.algo3.eventos.PreguntaCambioViewListener;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.items.ItemPregunta;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ListadoPreguntasVista extends ScrollPane {

    private final VBox cotenedor;
    private final ObservableList<Pregunta> preguntas;

    public ListadoPreguntasVista(ObservableList<Pregunta> preguntas) {
        this.cotenedor = new VBox();
        this.preguntas = preguntas;

        for (Pregunta pregunta: this.preguntas) {
            this.agregarPregunta(this.cotenedor.getChildren().size(), pregunta);
        }

        preguntas.addListener(new PreguntaCambioViewListener(this));

        this.setContent(this.cotenedor);
    }

    public void agregarPregunta(Integer index, Pregunta pregunta) {
        this.cotenedor.getChildren().add(index, new ItemPregunta(this.preguntas, pregunta));
    }

    public void sacarPregunta(int index) {
        System.out.println("a remover" + index);
        this.cotenedor.getChildren().remove(index);
    }
}
