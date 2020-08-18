package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

public class BotonOpcion extends Button {
    private Opcion opcion;

    public BotonOpcion(Opcion opcion, int ancho, int altura) {
        super(opcion.obtenerTitulo());
        this.opcion = opcion;
        this.setPrefSize(ancho, altura);
        this.setAlignment(Pos.CENTER);
        this.setWrapText(true);
    }

    public Opcion obtenerOpcion(){
        return this.opcion;
    }

}
