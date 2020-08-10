package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class CargarPreguntasVista extends EstructuraPrincipalVista {

    @Override
    protected Node cabeceraDerecha() {
        return new BotonEtiquetaVista("JUGADORES", true).obtenerNodo();
    }

    @Override
    protected Node cabeceraIzquierda() {
        return new BotonEtiquetaVista("INICIO", false).obtenerNodo();
    }

    @Override
    protected Node centro() {
        return new Label("Aca van el las preguntas");
    }

    @Override
    protected Node centroIzquierda() {
        return null;
    }
    @Override
    protected Node centroDerecha() {
        return null;
    }
}
