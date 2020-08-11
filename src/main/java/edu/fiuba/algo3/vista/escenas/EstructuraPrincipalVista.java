package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class EstructuraPrincipalVista implements Escena {

    private Scene escena;

    private CabeceraKahootVista crearCabecera() {
        return new CabeceraKahootVista(
                this.cabeceraIzquierda(),
                this.cabeceraDerecha()
        );
    }

    private void crearEscena() {
        BorderPane raiz = new BorderPane();

        CabeceraKahootVista cabecera = this.crearCabecera();

        raiz.setTop(cabecera.obtenerNodo());
        raiz.setLeft(this.centroIzquierda());
        raiz.setCenter(this.centro());
        raiz.setLeft(this.centroDerecha());
        //raiz.setBottom(this.piso());

        this.escena = new Scene(raiz);
    }

    public Scene obtenerEscena() {
        if (this.escena == null) this.crearEscena();
        return this.escena;
    }

    abstract protected Node cabeceraDerecha();

    abstract protected Node cabeceraIzquierda();

    abstract protected Node centro();

    abstract protected Node centroIzquierda();

    abstract protected Node centroDerecha();
}
