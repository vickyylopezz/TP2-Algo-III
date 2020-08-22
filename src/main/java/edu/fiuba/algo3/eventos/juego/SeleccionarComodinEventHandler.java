package edu.fiuba.algo3.eventos.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SeleccionarComodinEventHandler implements EventHandler<ActionEvent> {

    private final Jugada jugada;
    private final Comodin comodin;
    private final ObservableList<Node> botones;
    private final Button boton;

    public SeleccionarComodinEventHandler(Jugada jugada, Comodin comodin, ObservableList<Node> botones, Button boton) {
        this.jugada = jugada;
        this.comodin = comodin;
        this.botones = botones;
        this.boton = boton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try { this.jugada.seleccionarComodin(this.comodin);
        } catch (JugadorError | ComodinError error) {
            this.mensajeAlerta(error.getMessage());
            return;
        }
        for (Node bot: this.botones){ bot.setDisable(false); }
        boton.setDisable(true);
    }

    private void mensajeAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Movimiento invalido de comodin");
        alert.setHeaderText("El comodín no puede aplicarse a esta pregunta");
        alert.setContentText(mensaje);
        alert.getDialogPane().setPrefWidth(500);
        alert.showAndWait();
    }
}
