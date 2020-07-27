package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class MultipleChoiceParcial implements Pregunta {

    private String titulo;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Respuesta respuestaActual;

    public MultipleChoiceParcial(String titulo){
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
        ArrayList<Integer> puntajesPorJugador = new ArrayList<>();
        for (Respuesta respuestaDeCadaJugador : todasLasRespuestas){
            int puntajeParcial = 0;
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
    public void iniciar(Jugador jugador) {
        this.respuestaActual = new Respuesta(this, jugador);
    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws RespuestaError {
        this.respuestaActual.agregarOpcion(opcion);
    }

    @Override
    public Respuesta confirmar() {
        return this.respuestaActual;
    }

    @Override
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) {
        return 1;
    }
}
