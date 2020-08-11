package edu.fiuba.algo3.vista.componentes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface Boton extends Componente {
    void activar();

    void desactivar();

    void click(EventHandler<ActionEvent> accion);
}
