package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.GrupoDeComponentesVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.geometry.Pos.TOP_CENTER;

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
    protected Node centro() { return new GrupoDeComponentesVista("Jugador 2").obtenerNodo(); }

    @Override
    protected Node centroIzquierda() {
        return new GrupoDeComponentesVista("Jugador 1").obtenerNodo();
    }

    @Override
    protected Node centroDerecha() { return new BotonEtiquetaVista("CONFIRMAR",true).obtenerNodo(); }
}
