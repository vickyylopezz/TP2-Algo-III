package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.escenas.prejuego.IniciarJuegoVista;
import edu.fiuba.algo3.vista.escenas.juego.JuegoVista;
import edu.fiuba.algo3.vista.escenas.prejuego.RegistrarJugadoresVista;
import javafx.stage.Stage;

public class RegistrarJugadoresControlador {
    private RegistrarJugadoresVista vista;
    private Stage stage;

    public RegistrarJugadoresControlador(Stage stage) {
        this.stage = stage;
        this.vista = new RegistrarJugadoresVista(this);

        this.stage.setScene(this.vista.obtenerEscena());
    }

    public JuegoVista siguienteEscena() {
        return new JuegoVista(new JuegoControlador(this.stage));
    }

    public IniciarJuegoVista otraEscena() {
        return new IniciarJuegoVista(new IniciarJuegoControlador(this.stage));
    }

    public void confirmar(JugadorVista jugadorVista1, JugadorVista jugadorVista2) {
        if (jugadorVista1.esInvalido()){
            jugadorVista1.validarJugadorVista();
        } else {
            jugadorVista1.confirmarJugadorVista();
        }

        if (jugadorVista2.esInvalido()){
            jugadorVista2.validarJugadorVista();
        } else {
            jugadorVista2.confirmarJugadorVista();
        }

        if(!jugadorVista1.esInvalido() && !jugadorVista2.esInvalido()){
            this.siguienteEscena();
        }
    }
}
