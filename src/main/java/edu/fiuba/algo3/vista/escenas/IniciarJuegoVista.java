package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;

public class IniciarJuegoVista extends EstructuraPrincipalVista{

    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return null;
    }

    @Override
    protected Node centro() { return null; }

    @Override
    protected Node centroIzquierda() {
        return null;
    }

    @Override
    protected Node centroDerecha() {
        return new BotonEtiquetaVista("JUGAR", true).obtenerNodo(); }

}
