package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.escenas.prejuego.CargarPreguntasVista;
import edu.fiuba.algo3.vista.escenas.prejuego.IniciarJuegoVista;
import edu.fiuba.algo3.vista.escenas.prejuego.RegistrarJugadoresVista;
import javafx.stage.Stage;

public class IniciarJuegoControlador {

    private Stage stage;
    private IniciarJuegoVista vista;

    public IniciarJuegoControlador(Stage stage) {
        this.vista = new IniciarJuegoVista(this);
        this.stage = stage;

        this.stage.setScene(this.vista.obtenerEscena());
    }

    public void mostrarVista() { this.stage.show(); }

    public RegistrarJugadoresVista siguienteEscena() {
        return new RegistrarJugadoresVista(new RegistrarJugadoresControlador(this.stage));
    }

    public CargarPreguntasVista otraEscena() {
        return new CargarPreguntasVista(new CargarPreguntaControlador(this.stage));
    }
}
