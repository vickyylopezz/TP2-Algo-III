package edu.fiuba.algo3.vista.componentes.contenedores;

import edu.fiuba.algo3.eventos.preparacion.editorPreguntas.PreguntaCambioViewListener;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.items.ItemPregunta;
import edu.fiuba.algo3.vista.componentes.items.ItemSinElementos;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ListadoPreguntasVista extends ScrollPane {

    private final VBox cotenedor;
    private final ObservableList<Pregunta> preguntas;
    private Boolean hayItems;

    public ListadoPreguntasVista(ObservableList<Pregunta> preguntas) {
        this.cotenedor = new VBox();
        this.preguntas = preguntas;
        this.hayItems = !preguntas.isEmpty();

        for (Pregunta pregunta: this.preguntas) {
            this.agregarPregunta(this.cotenedor.getChildren().size(), pregunta);
        }

        preguntas.addListener(new PreguntaCambioViewListener(this));

        this.setContent(this.cotenedor);
        this.cargarEstilo();
    }

    private void cargarEstilo() {
        this.setFitToHeight(true);
        this.setFitToWidth(true);
    }

    public void agregarPregunta(Integer index, Pregunta pregunta) {
        if (!this.hayItems) {
            this.cotenedor.getChildren().clear();
            this.hayItems = true;
        }
        this.cotenedor.getChildren().add(index, new ItemPregunta(this.preguntas, pregunta));
    }

    public void sacarPregunta(int index) {
        this.cotenedor.getChildren().remove(index);
        if (this.cotenedor.getChildren().size() == 0) {
            this.hayItems = false;
            this.cotenedor.getChildren().add(new ItemSinElementos("No hay preguntas seleccionadas"));
        }
    }
}
