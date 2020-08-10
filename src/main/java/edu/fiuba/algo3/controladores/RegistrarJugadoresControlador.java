package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.escenas.IniciarJuegoVista;
import edu.fiuba.algo3.vista.escenas.JuegoVista;
import edu.fiuba.algo3.vista.escenas.RegistrarJugadoresVista;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegistrarJugadoresControlador {
    private RegistrarJugadoresVista vista;
    private Stage stage;

    private Label label = new Label();

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

    public void confirmar(ArrayList<TextField> textos) {
        /*for(TextField texto : textos){
            if (texto.getText().trim().equals("")) {

                this.label.setText("Debe ingresar un texto");
                this.label.setTextFill(Color.web("#FF0000"));
                texto.requestFocus();

            } else {

                this.label.setText(texto.getText());
                this.label.setTextFill(Color.web("#336600"));
            }
        }*/
        this.siguienteEscena();
    }
}
