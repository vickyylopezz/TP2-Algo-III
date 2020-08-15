package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;

import java.util.ArrayList;

public class ActualizadorPreguntasListener implements ListChangeListener<Pregunta> {

    private final ArrayList<Pregunta> preguntas;

    public ActualizadorPreguntasListener(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public void onChanged(Change<? extends Pregunta> cambio) {
        while (cambio.next()) {
            if (cambio.wasAdded()) this.agregarPreguntas(cambio);
            if (cambio.wasRemoved()) this.sacarPreguntas(cambio);
        }
    }

    private void sacarPreguntas(Change<? extends Pregunta> cambio) {
        this.preguntas.removeAll(cambio.getRemoved());
    }

    private void agregarPreguntas(Change<? extends Pregunta> cambio) {
        this.preguntas.addAll(cambio.getAddedSubList());
    }
}
