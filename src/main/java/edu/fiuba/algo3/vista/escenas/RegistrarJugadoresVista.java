package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.controladores.RegistrarJugadoresControlador;
import edu.fiuba.algo3.vista.componentes.GrupoDeComponentesVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class RegistrarJugadoresVista extends EstructuraPrincipalVista {
    private final RegistrarJugadoresControlador controlador;
    private ArrayList<TextField> textos = new ArrayList<>();

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
        GrupoDeComponentesVista grupoJugador = new GrupoDeComponentesVista("JUGADOR 2");
        this.textos.add(grupoJugador.obtenerTexto());

        return grupoJugador.obtenerNodo();
    }

    @Override
    protected Node centroIzquierda() {
        GrupoDeComponentesVista grupoJugador = new GrupoDeComponentesVista("JUGADOR 1");
        this.textos.add(grupoJugador.obtenerTexto());

        return grupoJugador.obtenerNodo();
    }

    @Override
    protected Node centroDerecha() {
        BotonCuadradoVista botonConfirmar = new BotonCuadradoVista("CONFIRMAR");
        botonConfirmar.click((event) -> this.controlador.confirmar(this.textos));

        return botonConfirmar.obtenerNodo();
    }
}
