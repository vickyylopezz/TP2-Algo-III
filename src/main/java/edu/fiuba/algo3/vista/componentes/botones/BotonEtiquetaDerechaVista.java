package edu.fiuba.algo3.vista.componentes.botones;

import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.Tema;
import edu.fiuba.algo3.vista.componentes.contenedores.EtiquetaVista;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BotonEtiquetaDerechaVista extends StackPane {

    private EtiquetaVista etiqueta;
    private BotonCircularVista boton;

    public BotonEtiquetaDerechaVista(String titulo) {
        this.etiqueta = new EtiquetaVista(new MiniTexto(titulo));
        this.boton = new BotonCircularVista(new ImageView(
                CargadorResources.obtenerImagen(Resources.IconoFlechaIzquierdaRuta())
        ));
        this.cargarEstilo();

        this.getChildren().addAll(this.etiqueta, this.boton);
    }

    private void cargarEstilo() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.boton.setTextFill(Tema.ColorBotonPrincipalTexto);

        etiqueta.setTranslateX(10);
        etiqueta.setAlignment(Pos.CENTER_RIGHT);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty () { return this.boton.onActionProperty(); }

    public EventHandler<ActionEvent> getOnAction() { return this.boton.getOnAction(); }

    public void setOnAction(EventHandler<ActionEvent> evento) { this.boton.setOnAction(evento); }
}
