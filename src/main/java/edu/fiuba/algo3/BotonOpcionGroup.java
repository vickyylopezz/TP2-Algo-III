package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class BotonOpcionGroup extends Button {
    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;

    public BotonOpcionGroup(Opcion opcionCorrecta, Opcion opcionIncorrecta, int ancho, int altura) {
        super(opcionCorrecta.obtenerTitulo());
        this.opcionCorrecta = opcionCorrecta;
        this.opcionIncorrecta = opcionIncorrecta;
        this.setPrefSize(ancho, altura);
        this.setAlignment(Pos.CENTER);
        this.setWrapText(true);
    }

    public Opcion obtenerOpcionCorrecta(){
        return this.opcionCorrecta;
    }
    public Opcion obtenerOpcionIncorrecta(){
        return this.opcionIncorrecta;
    }
}
