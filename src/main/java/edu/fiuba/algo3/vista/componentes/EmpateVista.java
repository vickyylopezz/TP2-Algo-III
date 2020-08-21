package edu.fiuba.algo3.vista.componentes;

import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


public class EmpateVista {
    protected Node nodo;
    public EmpateVista(Jugador jugador1, Jugador jugador2) {
        this.aplicarEstilo(jugador1,jugador2);
    }

    private void aplicarEstilo(Jugador jugador1, Jugador jugador2) {
        HBox empate = new HBox();

        JugadorEmpateVista jugadorUno = new JugadorEmpateVista(jugador1);
        JugadorEmpateVista jugadorDos = new JugadorEmpateVista(jugador2);

        empate.setSpacing(40);
        empate.setAlignment(Pos.CENTER);
        empate.getChildren().addAll(jugadorUno.obtenerNodo(),jugadorDos.obtenerNodo());

        this.nodo = empate;
    }

    public Node obtenerNodo(){
        return this.nodo;
    }
}
