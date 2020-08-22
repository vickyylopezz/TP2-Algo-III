package edu.fiuba.algo3.eventos.preparacion.editorJugadores;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.vista.componentes.JugadorVista;

public class ActualizadorVistaJugador implements JugadorObservador {

    private final JugadorVista vista;

    public ActualizadorVistaJugador(JugadorVista vista) { this.vista = vista; }

    @Override
    public void nuevoCambio(Jugador jugador, JugadorObservable jugadorObservable) {
        if (this.nombreJugadorValido(jugador)) {
            this.vista.desactivarNotificacion();
        } else {
            this.vista.activarNorificacion("Nombre invalido");
        }
    }

    private boolean nombreJugadorValido(Jugador jugador) {
        return !jugador.nombre().isBlank() && !jugador.nombre().isEmpty();
    }
}
