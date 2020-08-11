package edu.fiuba.algo3.vista.escenas.prejuego;

import edu.fiuba.algo3.controladores.RegistrarJugadoresControlador;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import edu.fiuba.algo3.vista.escenas.EstructuraPrincipalVista;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {

    private final RegistrarJugadoresControlador controlador;
    private JugadorVista jugadorVista1 = null;
    private JugadorVista jugadorVista2 = null;

    public RegistrarJugadoresVista(RegistrarJugadoresControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    protected Node cabeceraDerecha() {
        BotonEtiquetaVista botonConfirmar = new BotonEtiquetaVista("CONFIRMAR",true);
        botonConfirmar.click((event) -> this.controlador.confirmar(this.jugadorVista1,this.jugadorVista2));

        return botonConfirmar.obtenerNodo();
    }

    @Override
    protected Node cabeceraIzquierda() {
        BotonEtiquetaVista botonInicio = new BotonEtiquetaVista("INICIO",false);
        botonInicio.click((event) -> this.controlador.otraEscena());

        return botonInicio.obtenerNodo();
    }

    @Override
    protected Node centro() {
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.TOP_CENTER);

        this.jugadorVista1 = new JugadorVista("JUGADOR 1");
        this.jugadorVista2 = new JugadorVista("JUGADOR 2");

        contenedor.getChildren().addAll(
                this.jugadorVista1.obtenerNodo(),
                this.jugadorVista2.obtenerNodo()
        );

        return contenedor;
    }
}
