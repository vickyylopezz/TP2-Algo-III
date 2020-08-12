package edu.fiuba.algo3.vista.escenas.prejuego;

import edu.fiuba.algo3.controladores.IniciarJuegoControlador;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.escenas.EstructuraPrincipalVista;
import javafx.scene.Node;

public class IniciarJuegoVista extends EstructuraPrincipalVista {

    private final IniciarJuegoControlador controlador;

    public IniciarJuegoVista(IniciarJuegoControlador iniciarJuegoControlador) {
        this.controlador = iniciarJuegoControlador;
    }

    @Override
    protected Node cabeceraDerecha() {
        BotonEtiquetaIzquierdaVista botonCargarPreguntas = new BotonEtiquetaIzquierdaVista("CARGAR PREGUNTAS");
        botonCargarPreguntas.setOnAction((event) -> this.controlador.otraEscena());

        return botonCargarPreguntas;
    }

    @Override
    protected Node cabeceraIzquierda() {
        return null;
    }

    @Override
    protected Node centro() {
        BotonCuadradoVista botonIniciar = new BotonCuadradoVista("INICIAR");
        botonIniciar.setOnAction((event) -> this.controlador.siguienteEscena());
        return botonIniciar;
    }
}
