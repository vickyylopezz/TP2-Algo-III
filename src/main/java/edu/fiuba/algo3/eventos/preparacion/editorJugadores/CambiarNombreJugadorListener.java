package edu.fiuba.algo3.eventos.preparacion.editorJugadores;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CambiarNombreJugadorListener implements ChangeListener<String> {

    private final JugadorObservable jugador;

    public CambiarNombreJugadorListener(JugadorObservable jugador) {
        this.jugador = jugador;
    }

    @Override
    public void changed(ObservableValue<? extends String> obs, String nombreViejo, String nombreNuevo) {
        this.jugador.cambiarNombre(nombreNuevo);
    }
}
