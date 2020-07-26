package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class VerdaderoFalsoClasico implements Pregunta {

    private ArrayList<Opcion> opciones;
    private String titulo;
    private Respuesta respuestaActual;

    public VerdaderoFalsoClasico(String pregunta){
        this.opciones = new ArrayList<>();
        this.titulo = pregunta;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 1);
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 0);
        this.opciones.add(opcion);
    }

    public ArrayList<Integer> puntajeConRespuestas(ArrayList<Respuesta> todasLasRespuestas) {
        ArrayList<Integer> puntajes = new ArrayList<>();
        for(Respuesta respuestaPorJugador : todasLasRespuestas){
            for (Opcion opcion : respuestaPorJugador.obtenerOpcionesElegidas()){
                puntajes.add(opcion.getValor());
            }
        }
        return puntajes;
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

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    public String titulo() {
        return this.titulo;
    }
}
