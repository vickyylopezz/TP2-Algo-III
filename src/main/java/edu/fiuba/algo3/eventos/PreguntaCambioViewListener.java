package edu.fiuba.algo3.eventos;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.componentes.contenedores.ListadoPreguntasVista;
import javafx.collections.ListChangeListener;

public class PreguntaCambioViewListener implements ListChangeListener<Pregunta> {

    private final ListadoPreguntasVista listadoVista;

    public PreguntaCambioViewListener(ListadoPreguntasVista listadoVista) {
        this.listadoVista = listadoVista;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Pregunta> cambio) {
        while (cambio.next()) {
            if (cambio.wasAdded()) this.agregarPreguntas(cambio);
            if (cambio.wasRemoved()) this.sacarPreguntas(cambio);
        }
    }

    private void sacarPreguntas(Change<? extends Pregunta> cambio) {
        for (Pregunta pregunta: cambio.getRemoved()) {
            this.listadoVista.sacarPregunta(cambio.getList().indexOf(pregunta));
        }
    }

    private void agregarPreguntas(Change<? extends Pregunta> cambio) {
        for (Pregunta pregunta: cambio.getAddedSubList()) {
            this.listadoVista.agregarPregunta(cambio.getList().indexOf(pregunta), pregunta);
        }
    }

}
