package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;

public class IniciarJuegoVista extends EstructuraPrincipalVista{

    @Override
    protected Node cabeceraDerecha() { return null; }

    @Override
    protected Node cabeceraIzquierda() {
        return new BotonEtiquetaVista("JUGADORES",false).obtenerNodo();
    }

    @Override
    protected Node centro() { return new BotonCuadradoVista("JUGAR").obtenerNodo(); }

    @Override
    protected Node centroIzquierda() {
        return null;
    }

    @Override
    protected Node centroDerecha() { return null; }
}
