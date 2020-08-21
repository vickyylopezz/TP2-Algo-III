package edu.fiuba.algo3.vista.escenas;

import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.ContenedorSonido;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;

public abstract class BaseLayout extends BorderPane {

    protected final CabeceraKahootVista cabecera;
    protected BotonEtiquetaIzquierdaVista botonSigueinte;
    protected BotonEtiquetaDerechaVista botonAnterior;

    public BaseLayout(MediaPlayer reproductor) {
        this.cabecera = new CabeceraKahootVista();
        this.setTop(this.cabecera);

        ContenedorSonido botoneraSonido = new ContenedorSonido(reproductor);
        this.setBottom(botoneraSonido.obtenerNodo());

        this.setStyle("-fx-background-image: url(" + Resources.FondoPrincipalRuta() + ");" + "-fx-background-size: cover");
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
        this.botonSiguienteDefinido();
    }

    public void eventoAnterior(EventHandler<ActionEvent> evento, String titulo) {
        this.botonAnterior = null;

        if (evento == null) this.cabecera.definirPanelIzquierdo(null);

        String tituloBoton = (titulo != null) ? titulo : "Volver";
        this.botonAnterior = new BotonEtiquetaDerechaVista(tituloBoton);
        this.botonAnterior.setOnAction(evento);
        this.cabecera.definirPanelIzquierdo(this.botonAnterior);
        this.botonAnteriorDefinido();
    }

    protected void botonSiguienteDefinido() { }

    protected void botonAnteriorDefinido() { }
}
