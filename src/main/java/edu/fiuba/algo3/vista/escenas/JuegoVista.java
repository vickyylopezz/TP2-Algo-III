package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.JuegoControlador;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import javafx.scene.Node;

public class JuegoVista extends EstructuraPrincipalVista{
    private JuegoControlador controlador;

    public JuegoVista(JuegoControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    protected Node cabeceraDerecha() {
        return null;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return null;
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
