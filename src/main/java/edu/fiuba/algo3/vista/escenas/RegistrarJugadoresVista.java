package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.RegistrarJugadoresControlador;
import edu.fiuba.algo3.vista.componentes.GrupoDeComponentesVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {
    private final RegistrarJugadoresControlador controlador;

    public RegistrarJugadoresVista(RegistrarJugadoresControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    protected Node cabeceraDerecha() { return null; }

    @Override
    protected Node cabeceraIzquierda() {
        BotonEtiquetaVista botonInicio = new BotonEtiquetaVista("INICIO",false);
        botonInicio.obtenerBoton().click((event) -> this.controlador.otraEscena());

        return botonInicio.obtenerNodo();
    }

    @Override
    protected Node centro() { return new GrupoDeComponentesVista("JUGADOR 2").obtenerNodo(); }

    @Override
    protected Node centroIzquierda() {
        return new GrupoDeComponentesVista("JUGADOR 1").obtenerNodo();
    }

    @Override
    protected Node centroDerecha() {
        BotonCuadradoVista botonConfirmar = new BotonCuadradoVista("CONFIRMAR");
        botonConfirmar.click((event) -> this.controlador.siguienteEscena());

        return botonConfirmar.obtenerNodo();
    }
}
