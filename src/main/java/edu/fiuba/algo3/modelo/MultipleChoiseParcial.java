package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class MultipleChoiseParcial {

    private String titulo;
    private ArrayList<Opcion> opciones = new ArrayList<Opcion>();

    public MultipleChoiseParcial(String titulo){
        this.titulo = titulo;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 1);
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 0);
        this.opciones.add(opcion);
    }

    public String titulo() {
        return this.titulo;
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    public ArrayList<Integer> obtenerPuntajes(ArrayList<ArrayList<Opcion>> todasLasRespuestas) {
        ArrayList<Integer> puntajesPorJugador = new ArrayList<Integer>();
        for (ArrayList<Opcion> respuestaDeCadaJugador : todasLasRespuestas){
            Integer puntajeParcial = 0;
            for (Opcion opcionElegida : respuestaDeCadaJugador){
                puntajeParcial = puntajeParcial + opcionElegida.getValor();
            }
            if (puntajeParcial != respuestaDeCadaJugador.size()) {
                puntajesPorJugador.add(0);
            } else {
                puntajesPorJugador.add(puntajeParcial);
            }
        }
        return puntajesPorJugador;
    }
}
