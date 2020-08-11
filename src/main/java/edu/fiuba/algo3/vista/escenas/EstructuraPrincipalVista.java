package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class EstructuraPrincipalVista {

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
        raiz.setCenter(this.centro());

        this.escena = new Scene(raiz, 800, 600);
    }

    public Scene obtenerEscena() {
        if (this.escena == null) this.crearEscena();
        return this.escena;
    }

    abstract protected Node cabeceraDerecha();

    abstract protected Node cabeceraIzquierda();

    abstract protected Node centro();
}
