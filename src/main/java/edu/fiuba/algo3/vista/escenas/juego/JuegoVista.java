package edu.fiuba.algo3.vista.escenas.juego;

import edu.fiuba.algo3.controladores.JuegoControlador;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.escenas.EstructuraPrincipalVista;
import javafx.scene.Node;

public class JuegoVista extends EstructuraPrincipalVista {

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
        BotonEtiquetaDerechaVista boton = new BotonEtiquetaDerechaVista("JUGADORES");
        boton.setOnAction((event) -> this.controlador.otraEscena());
        return boton;
    }

    @Override
    protected Node centro() { return new BotonCuadradoVista("Iniciar Juego"); }
}
