package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class EstructuraPrincipalVista {

    private Scene escena;

    public Scene obtenerEscena() {
        if (this.escena == null) this.crearEscena();
        return this.escena;
    }

    private void crearEscena() {
        BorderPane raiz = new BorderPane();

        raiz.setTop(new CabeceraKahootVista(
                this.cabeceraIzquierda(),
                this.cabeceraDerecha()
        ));
        raiz.setCenter(this.centro());

        this.escena = new Scene(raiz, 800, 600);
    }

    abstract protected Node cabeceraIzquierda();

    abstract protected Node cabeceraDerecha();

    abstract protected Node centro();
}
