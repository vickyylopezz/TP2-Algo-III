package edu.fiuba.algo3.modelo.preguntas.opcion;

import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;


public class OpcionGroupChoice extends Opcion {
    protected Grupo grupoPertenece;

    public OpcionGroupChoice(String titulo, Punto punto, Grupo grupo){
        this.titulo = titulo;
        this.punto = punto;
        this.grupoPertenece = grupo;
    }

    public Grupo obtenerGrupo(){return this.grupoPertenece;}

}
