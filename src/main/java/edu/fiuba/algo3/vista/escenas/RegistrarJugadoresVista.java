package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {
    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return null;
    }

    @Override
    protected Node centro() {
        return null;
    }

    @Override
    protected Node centroIzquierda() {
        return new BotonEtiquetaVista("JUGADOR 1", true).obtenerNodo();
    }

    @Override
    protected Node centroDerecha() {
        return new BotonEtiquetaVista("JUGADOR 2", true).obtenerNodo();
    }

}
