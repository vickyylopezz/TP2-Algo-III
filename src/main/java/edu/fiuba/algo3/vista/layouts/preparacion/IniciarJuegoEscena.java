package edu.fiuba.algo3.vista.layouts.preparacion;

import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.layouts.BaseEscena;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class IniciarJuegoEscena extends BaseEscena {

    private StackPane cuerpo;

    public IniciarJuegoEscena() {
        super();

        this.cuerpo = new StackPane();
        this.raiz.setCenter(this.cuerpo);
    }

    public void eventoBotonPrincipal(EventHandler<ActionEvent> evento) {
        if (evento == null) this.cuerpo.getChildren().clear();

        String tituloBoton = "Iniciar Juego";
        BotonCuadradoVista boton = new BotonCuadradoVista(tituloBoton);
        boton.setOnAction(evento);

        this.cuerpo.getChildren().add(boton);
    }
}
