package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class VerdaderoFalsoConPenalidad implements Pregunta {
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private String titulo;
    private Respuesta respuestaActual;

    public VerdaderoFalsoConPenalidad(String titulo,boolean VerdaderoCorrecta){
        this.titulo = titulo;
        if(VerdaderoCorrecta){
            Opcion opcionVerdadero = new Opcion("Verdadero", 1);
            Opcion opcionFalso = new Opcion("Falso", -1);
            this.opciones.add(opcionVerdadero);
            this.opciones.add(opcionFalso);
        }else{
            Opcion opcionVerdadero = new Opcion("Verdadero", -1);
            Opcion opcionFalso = new Opcion("Falso", 1);
            this.opciones.add(opcionVerdadero);
            this.opciones.add(opcionFalso);
        }
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override //Falta implementarla
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError {
        return null;
    }

    @Override
    public ArrayList<Integer> puntajeConRespuestas(ArrayList<Respuesta> respuestas) throws PreguntaError {
        ArrayList<Integer> puntajes = new ArrayList<Integer>();
        for (Respuesta respuesta: respuestas) {
            puntajes.add(this.puntajeConOpciones(respuesta.opcionesElegidas));
        }
        return puntajes;
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
        Respuesta resultado = this.respuestaActual;
        this.respuestaActual = null;
        return resultado;
    }


}
