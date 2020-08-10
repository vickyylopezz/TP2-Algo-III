package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.escenas.IniciarJuegoVista;
import edu.fiuba.algo3.vista.escenas.JuegoVista;
import edu.fiuba.algo3.vista.escenas.RegistrarJugadoresVista;
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
}
