package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.preguntas.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class OpcionGroupChoice extends Opcion {
    protected Grupo grupoPertenece;
    protected Grupo grupoElegido;

    public OpcionGroupChoice(String titulo, Punto punto, Grupo grupo){
        this.titulo = titulo;
        this.punto = punto;
        this.grupoPertenece = grupo;
    }

    public void agregarGrupo(Grupo grupo){
        this.grupoElegido = grupo;
    }

    public boolean grupoCoincide(){
        return this.grupoPertenece.equals(this.grupoElegido);
    }
}
