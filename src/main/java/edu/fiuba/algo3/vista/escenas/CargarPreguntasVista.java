package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquietaVista;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class CargarPreguntasVista extends EstructuraPrincipalVista {

    @Override
    protected Node cabeceraDerecha() {
        return new BotonEtiquietaVista("Jugadores", true).obtenerNodo();
    }

    @Override
    protected Node cabeceraIzquierda() {
        return new BotonEtiquietaVista("Inicio", false).obtenerNodo();
    }

    @Override
    protected Node centro() {
        return new Label("Aca van el las preguntas");
    }
}
