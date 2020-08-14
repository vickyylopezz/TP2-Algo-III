package edu.fiuba.algo3.vista.layouts;

import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public abstract class BaseEscena extends Scene {

    protected final BorderPane raiz;
    protected final CabeceraKahootVista cabecera;
    protected BotonEtiquetaIzquierdaVista botonSigueinte;
    protected BotonEtiquetaDerechaVista botonAnterior;

    public BaseEscena() {
        super(new Label("Loading"), 800, 600);

        this.raiz = new BorderPane();
        this.cabecera = new CabeceraKahootVista();

        this.raiz.setTop(this.cabecera);
        this.setRoot(this.raiz);
    }

    public void eventoSiguiente(EventHandler<ActionEvent> evento) {
        this.eventoSiguiente(evento, null);
    }

    public void eventoAnterior(EventHandler<ActionEvent> evento) {
        this.eventoAnterior(evento, null);
    }

    public void eventoSiguiente(EventHandler<ActionEvent> evento, String titulo) {
        this.botonSigueinte = null;

        if (evento == null) this.cabecera.definirPanelDerecho(null);

        String tituloBoton = (titulo != null) ? titulo : "Siguiente";
        this.botonSigueinte = new BotonEtiquetaIzquierdaVista(tituloBoton);
        this.botonSigueinte.setOnAction(evento);
        this.cabecera.definirPanelDerecho(this.botonSigueinte);
    }

    public void eventoAnterior(EventHandler<ActionEvent> evento, String titulo) {
        this.botonAnterior = null;

        if (evento == null) this.cabecera.definirPanelIzquierdo(null);

        String tituloBoton = (titulo != null) ? titulo : "Volver";
        this.botonAnterior = new BotonEtiquetaDerechaVista(tituloBoton);
        this.botonAnterior.setOnAction(evento);
        this.cabecera.definirPanelIzquierdo(this.botonAnterior);
    }
}
