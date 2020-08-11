package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.RegistrarJugadoresControlador;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {
    private final RegistrarJugadoresControlador controlador;
    private JugadorVista jugadorVista1 = null;
    private JugadorVista jugadorVista2 = null;

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
    protected Node centro() {
        JugadorVista grupoJugador = new JugadorVista("JUGADOR 2");
        jugadorVista1 = grupoJugador;

        return grupoJugador.obtenerNodo();
    }

    @Override
    protected Node centroIzquierda() {
        JugadorVista grupoJugador = new JugadorVista("JUGADOR 1");
        jugadorVista2 = grupoJugador;

        return grupoJugador.obtenerNodo();
    }

    @Override
    protected Node centroDerecha() {
        BotonCuadradoVista botonConfirmar = new BotonCuadradoVista("CONFIRMAR");
        botonConfirmar.click((event) -> this.controlador.confirmar(this.jugadorVista1,this.jugadorVista2));

        return botonConfirmar.obtenerNodo();
    }
}
