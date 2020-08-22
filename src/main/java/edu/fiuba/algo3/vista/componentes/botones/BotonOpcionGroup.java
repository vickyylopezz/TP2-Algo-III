package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class BotonOpcionGroup extends Button {
    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;

    public BotonOpcionGroup(Opcion opcionCorrecta, Opcion opcionIncorrecta, int ancho, int altura) {
        super(opcionCorrecta.obtenerTitulo());
        this.opcionCorrecta = opcionCorrecta;
        this.opcionIncorrecta = opcionIncorrecta;
        this.setPrefSize(ancho, altura);
        this.setAlignment(Pos.CENTER);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setStyle("-fx-font-size: 18px");
        this.setWrapText(true);
    }

    public Opcion obtenerOpcionCorrecta(){
        return this.opcionCorrecta;
    }
    public Opcion obtenerOpcionIncorrecta(){
        return this.opcionIncorrecta;
    }
}
