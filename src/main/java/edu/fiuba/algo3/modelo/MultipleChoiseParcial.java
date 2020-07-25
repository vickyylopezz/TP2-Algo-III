package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class MultipleChoiseParcial implements Pregunta {

    private String titulo;
    private ArrayList<Opcion> opciones = new ArrayList<Opcion>();
    private Respuesta respuestaActual;

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

    public ArrayList<Integer> puntajeConRespuestas(ArrayList<Respuesta> todasLasRespuestas) {
        ArrayList<Integer> puntajesPorJugador = new ArrayList<Integer>();
        for (Respuesta respuestaDeCadaJugador : todasLasRespuestas){
            Integer puntajeParcial = 0;
            for (Opcion opcionElegida : respuestaDeCadaJugador.obtenerOpcionesElegidas()){
                puntajeParcial = puntajeParcial + opcionElegida.getValor();
            }
            if (puntajeParcial != (respuestaDeCadaJugador.obtenerOpcionesElegidas()).size()) {
                puntajesPorJugador.add(0);
            } else {
                puntajesPorJugador.add(puntajeParcial);
            }
        }
        return puntajesPorJugador;
    }

    @Override
    public void iniciar(Jugador jugador) throws PreguntaError {
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError {
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar() throws PreguntaError {
        return this.respuestaActual;
    }

    @Override
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError{
        return 1;
    }
}
