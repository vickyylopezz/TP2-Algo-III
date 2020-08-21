package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class BotonOpcionClasica extends Button {
    private Opcion opcion;

    public BotonOpcionClasica(Opcion opcion, int ancho, int altura) {
        super(opcion.obtenerTitulo());
        this.opcion = opcion;
        this.setPrefSize(ancho, altura);
        this.setAlignment(Pos.CENTER);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setStyle("-fx-font-size: 18px");
        this.setWrapText(true);
    }

    public Opcion obtenerOpcion(){
        return this.opcion;
    }

}
