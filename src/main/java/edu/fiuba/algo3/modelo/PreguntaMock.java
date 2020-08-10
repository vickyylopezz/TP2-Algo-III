package edu.fiuba.algo3.modelo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class PreguntaMock implements Pregunta{

    @Override
    public Integer puntajeConOpciones(ArrayList<Opcion> opciones) throws PreguntaError {
        return null;
    }

    @Override
    public void iniciar(Jugador jugador) throws PreguntaError {

    }

    @Override
    public void seleccionarOpcion(Opcion opcion) throws PreguntaError {

    }

    @Override
    public Respuesta confirmar() throws PreguntaError {
        return null;
    }

    @Override
    public void extraerOpciones(JsonObject object){
    }

    @Override
    public String obtenerTitulo(){
        return null;
    }

    @Override
    public ArrayList<Opcion> obtenerOpciones(){
        return null;
    }
}
