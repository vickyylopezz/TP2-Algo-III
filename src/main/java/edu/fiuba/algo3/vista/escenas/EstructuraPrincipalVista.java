package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class EstructuraPrincipalVista implements Escena {

    private Scene escena;
    private CabeceraKahootVista cabecera;

    public EstructuraPrincipalVista() {
        this.crearCabecera();
        this.crearEscena();
    }

    private void crearCabecera() {
        this.cabecera = new CabeceraKahootVista(
                this.cabeceraIzquierda(),
                this.cabeceraDerecha()
        );
    }

    private void crearEscena() {
        BorderPane raiz = new BorderPane();

        raiz.setTop(this.cabecera.obtenerNodo());
        raiz.setLeft(this.centroIzquierda());
        raiz.setCenter(this.centro());
        raiz.setLeft(this.centroDerecha());
        //raiz.setBottom(this.piso());

        this.escena = new Scene(raiz, 800, 600);
    }

    public Scene obtenerEscena() { return this.escena; }

    abstract protected Node cabeceraDerecha();

    abstract protected Node cabeceraIzquierda();

    abstract protected Node centro();

    abstract protected Node centroIzquierda();

    abstract protected Node centroDerecha();
}
